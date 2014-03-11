package jatrace.gui;

import jatrace.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyBuilder
		extends JFrame
		implements ActionListener, FocusListener
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
		
		//setup label maker title
		bodyNamer = new JPanel(new BorderLayout());
		JLabel l = new JLabel("Body Label",JLabel.CENTER);
		l.setPreferredSize(new Dimension(100,25));
		bodyNamer.add(l, BorderLayout.PAGE_START);
		
		
		//setup up text input field
		String t = parent.getText();
		textInput = new JTextField(20);
		textInput.setText(t);
		textInput.setPreferredSize(new Dimension(0,25));
		textInput.addActionListener(this);
		textInput.addFocusListener(this);
		bodyNamer.add(textInput, BorderLayout.CENTER);
		
		//setup text input button
		textButton.setPreferredSize(new Dimension(100,25));
		textButton.addActionListener(this);
		bodyNamer.add(textButton, BorderLayout.LINE_END);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object s = e.getSource();
		
		if (s == textInput || s == textButton)
		{
			String t = textInput.getText();
			parent.setText(t);
		}
		
		//System.out.println("we're supposed to build a body now");
	}
	
	//Focus listener stuff below
	public void focusGained(FocusEvent e)
	{
		Component c = e.getComponent();
		if (c instanceof JTextField)
		{
			((JTextField)c).selectAll();
		}
	}
	
	public void focusLost(FocusEvent e) { }
	
}
