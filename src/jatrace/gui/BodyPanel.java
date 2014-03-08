package jatrace.gui;

import jatrace.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyPanel extends JPanel implements ActionListener
{
	
	private BodyButton bodysource;
	private int bodyCount = 0;
	
	private JPanel buttonspanel;
	private JButton addbutton;

	
	public BodyPanel()
	{
		super(new BorderLayout());
		setPreferredSize( new Dimension(200,400) );
		setMaximumSize( new Dimension(200,0) );
		
		setupButton();
		setupButtonsPanel();
		
		bodyCount = 0;
	}
	
	private void setupButton()
	{
		addbutton = new JButton("New Body");
		addbutton.setMinimumSize(new Dimension(200,50));
		addbutton.setMaximumSize(new Dimension(200,50));
		addbutton.addActionListener(this);
		add(addbutton, BorderLayout.PAGE_END);
	}
	
	private void setupButtonsPanel()
	{
		buttonspanel = new JPanel();
		buttonspanel.setLayout(null);
		
		addNewBodyButton();
		
		JScrollPane p = new JScrollPane(buttonspanel);
		p.setPreferredSize( new Dimension(200,345) );
		p.setMaximumSize( new Dimension(200,0) );
		p.setMinimumSize( new Dimension(200,0) );
		
		add(p, BorderLayout.CENTER);
	}
	
	private void addNewBodyButton()
	{
		BodyButton b = new BodyButton();
		b.setLocation(0,55 * bodyCount);
		bodysource.insertBefore(b);
		bodysource = b;
		
		bodyCount += 1;
		buttonspanel.setSize(180,55 * bodyCount - 5);
		buttonspanel.add(b);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object s = e.getSource();
		
		if (s == addbutton)
		{
			addNewBodyButton();
		}
	}
}
