package jatrace.gui;

import jatrace.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyBuilder extends JFrame implements ActionListener
{
	
	private BodyPasser parent = null;
	private JPanel body;
	private JPanel bodyNamer;
	
	private JTextField textInput;
	private JButton textButton = new JButton("set name");
	
	public BodyBuilder(BodyPasser p)
	{
		super();
		
		//let body button listening to this frame handle close operations
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//register which bodypasser this frame points to
		parent = p;
		
		body = new JPanel(new BorderLayout());
		body.setPreferredSize(new Dimension(500,200));
		
		setupBodyNamer();
		body.add(bodyNamer, BorderLayout.PAGE_START);
		
		getContentPane().add(body);
		pack();
		
	}
	
	private void setupBodyNamer()
	{
		
		//set up the button title text input area
		bodyNamer = new JPanel(new BorderLayout());
		JLabel l = new JLabel("Body Name");
		l.setPreferredSize(new Dimension(100,25));
		bodyNamer.add(l, BorderLayout.LINE_START);
		
		JPanel p = new JPanel(new BorderLayout());
		bodyNamer.add(p, BorderLayout.CENTER);
		
		textButton.setPreferredSize(new Dimension(100,25));
		textButton.addActionListener(this);
		p.add(textButton, BorderLayout.LINE_END);
		
		String t = parent.getText();
		textInput = new JTextField(20);
		textInput.setText(t);
		textInput.setPreferredSize(new Dimension(0,25));
		p.add(textInput, BorderLayout.CENTER);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object s = e.getSource();
		
		if (s == textButton)
		{
			String t = textInput.getText();
			parent.setText(t);
		}
		
		//System.out.println("we're supposed to build a body now");
	}
	
}
