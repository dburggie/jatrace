package jatrace.gui;

import jatrace.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyBuilder extends JFrame implements ActionListener
{
	
	
	
	private BodyPasser parent = null;
	
	public BodyBuilder(BodyPasser p)
	{
		super();
		parent = p;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object s = e.getSource();
		
		if (s != this) return;
		
		System.out.println("we're supposed to build a body now");
	}
}
