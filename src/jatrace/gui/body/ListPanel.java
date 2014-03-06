package jatrace.gui.body;

import jatrace.*;
import jatrace.bodies.*;
import jatrace.gui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ListPanel extends MyPanel implements ActionListener
{
	
	public ListPanel()
	{
		super(5,5,290,580);
	}
	
	public void addNewBody()
	{
		//add a body here
	}
	
	public LinkedBody getWorldBodies()
	{
		//return world bodies
		return null;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		Object source = event.getSource();
		
		
	}
	
	
}
