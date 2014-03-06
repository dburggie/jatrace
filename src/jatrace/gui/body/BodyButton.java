package jatrace.gui.body;

import jatrace.*;
import jatrace.bodies.*;
import jatrace.gui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyButton extends MyButton
{
	
	Body wrappedBody = null;
	
	public static enum Type { ADD, EDIT, DETAILS, REMOVE, NULL };
	public Type type = Type.NULL;
	
	public BodyButton(int x, int y, Type t)
	{
		super(0,0,0,0,"");
		
		String text = "";
		if (t == Type.ADD)
		{
			this.setSize(100,20);
			this.setText("Add new Body");
			this.setType(Type.ADD);
		}
		if (t == Type.EDIT)
		{
			this.setSize(50,20);
			this.setText("edit");
			this.setType(Type.EDIT);
		}
		if (t == Type.REMOVE)
		{
			this.setSize(20,20);
			this.setText("X");
			this.setType(Type.REMOVE);
		}
		if (t == Type.DETAILS)
		{
			this.setSize(20,20);
			this.setText("view");
			this.setType(Type.DETAILS);
		}
	}
	
	public void setType(Type t) { type = t; }
	
	private BodyButton nextInChain;
	private BodyButton prevInChain;
	
	/** Get the next object in the chain. */
	public BodyButton nextLink()
	{
		return nextInChain;
	}
	
	/** Get the previous object in the chain. */
	public BodyButton prevLink()
	{
		return prevInChain;
	}
	
	/** Insert a new link before this one. */
	public void insertBefore(BodyButton obj)
	{
		if (prevInChain != obj)
		{
			prevInChain = obj;
			
			if (obj != null)
			{
				obj.insertAfter(this);
			}
		}
	}
	
	/** Insert a new link after this one. */
	public void insertAfter(BodyButton obj)
	{
		if (nextInChain != obj)
		{
			nextInChain = obj;
			
			if (obj != null)
			{
				obj.insertBefore(this);
			}
		}
	}
	
	public void removeFromChain()
	{
		if (prevInChain != null)
		{
			prevInChain.insertBefore(nextInChain);
		} 
		else if (nextInChain != null)
		{
			nextInChain.insertAfter(prevInChain);
		}
	}
	
	private int buttonID;
	public void setButtonID(int ID) 
	{
		buttonID = ID;
	}
	
	public int getButtonID() { return buttonID; }
	
	public BodyButton findButtonByID(int ID)
	{
		if (this.buttonID == ID)
		{
			return this;
		}
		
		BodyButton next = nextInChain;
		
		if (next != null)
		{
			return next.findButtonByID(ID);
		}
		
		return null;
		
	}
}
