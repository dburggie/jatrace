package jatrace.gui;

import jatrace.*;
import jatrace.bodies.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Gives functionality to add/remove/edit bodies in the world within the
 *  jatrace gui front end. */
public class BodiesPanel extends MyPanel implements ActionListener
{
	
	public static final int minWidth = 100, minHeight = 100;
	
	BodyButton buttons;
	int assignedIDs = 0;
	public void setButtonID(BodyButton b)
	{
		b.setButtonID(assignedIDs);
		assignedIDs += 1;
	}
	
	public BodiesPanel()
	{
		super(0,0,300,600);
		buttons = new BodyButton(200,580,BodyButton.Type.ADD);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		Object source = event.getSource();
		
		BodyButton b = buttons;
		while (b != null)
		{
			if (b == source)
			{
				this.handleButton(b);
			}
			b = b.nextLink();
			
		}
		
	}
	
	private void handleButton( BodyButton b )
	{
		if (b.type == BodyButton.Type.REMOVE)
		{
			//remove the body
			//remove the buttons for the body
			//update layout of the panel
		}
		
		if (b.type == BodyButton.Type.ADD)
		{
			//get body parameters
			//add the new body
			//get buttons for body
			//update panel layout
		}
		
		if (b.type == BodyButton.Type.EDIT)
		{
			//get edit parameters
			//set new body parameters
			//update panel layout
		}
		
		if (b.type == BodyButton.Type.DETAILS)
		{
			//make new popup frame
			//fill with body parameters
		}
	}
}
