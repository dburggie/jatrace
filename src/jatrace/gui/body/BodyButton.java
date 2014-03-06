package jatrace.gui.body;

import jatrace.*;
import jatrace.bodies.*;
import jatrace.gui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyButton extends MyButton
{
	
	public static enum Type { EDIT, REMOVE, NULL };
	private Type type = Type.NULL;
	
	public BodyButton(Type t)
	{
		
		super("button");
		switch (t)
		{
			case EDIT:
				this.setText("Edit");
				this.setSize(75,20);
				this.setType(t);
				break;
			case REMOVE:
				this.setText("X");
				this.setSize(20,20);
				this.setType(t);
				break;
			default:
				break;
		}
		
	}
	
	public void setType(Type t) { type = t; }
	public Type getType() { return type; }
	
}
