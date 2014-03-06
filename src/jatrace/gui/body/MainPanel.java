package jatrace.gui.body;

import jatrace.*;
import jatrace.bodies.*;
import jatrace.gui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Gives functionality to add/remove/edit bodies in the world within the
 *  jatrace gui front end. */
public class MainPanel extends MyPanel implements ActionListener
{
	
	public static final int minWidth = 100, minHeight = 100;
	
	private ListPanel bodylist;
	private MyButton addbutton;
	
	public MainPanel()
	{
		super(0,0,300,600);
		//BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)
		addbutton = new MyButton(100, 580, 100, 20, "New Body");
		addbutton.addActionListener(this);
	}
	
	/** Get a LinkedBody Object that contains all of the worlds bodies. */
	public LinkedBody getWorldBodies()
	{
		return bodylist.getWorldBodies();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		Object source = event.getSource();
		
		if (source == addbutton)
		{
			bodylist.addNewBody();
		}
		
	}
}
