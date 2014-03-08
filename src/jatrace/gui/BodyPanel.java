package jatrace.gui;

import jatrace.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyPanel extends JPanel implements ActionListener
{
	
	private BodyButton bodysource;
	private int bodyCount = 0;
	
	private JScrollPane buttonscrollpane;
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
		buttonscrollpane = p;
		
		add(buttonscrollpane, BorderLayout.CENTER);
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
	
	private void updateButtonsPanel()
	{
		JPanel bp = new JPanel();
		bp.setLayout(null);
		bp.setSize(180, 55 * bodyCount - 5);
		BodyButton b = bodysource;
		
		for (int count = 0; b != null; count += 1)
		{
			b.setLocation(0,55 * count);
			bp.add(b);
			b = b.getNext();
		}
		
		buttonscrollpane.remove(buttonspanel);
		buttonspanel = bp;
		buttonscrollpane.add(buttonspanel);
		buttonscrollpane.revalidate();
		
	}
	
	public void removeButton(BodyButton b)
	{
		BodyButton temp = bodysource;
		
		while (temp != null)
		{
			if ( b == temp )
			{
				temp.remove();
				bodyCount -= 1;
				updateButtonsPanel();
				break;
			}
			temp = temp.getNext();
		}
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
