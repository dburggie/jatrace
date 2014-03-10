package jatrace.gui;

import jatrace.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BodyButton extends JButton implements ActionListener, BodyPasser
{
	private Body body;
	private String text;
	BodyBuilder bb;
	boolean builderLocked = false;
	 
	public BodyButton()
	{
		super();
		setPreferredSize(new Dimension(0,25));
		body = null;
		bb = new BodyBuilder(this);
		setText( "Empty Body" );
		addActionListener(this);
	}
	 
	@Override
	public Body getBody() { return body; }
	 
	@Override
	public void setBody(String t, Body b)
	{
		setText(t);
		body = b;
		builderLocked = false;
		update();
	}
	 
	public void actionPerformed(ActionEvent e)
	{
		Object s = e.getSource();
		if (s == this)
		{
			if (!builderLocked)
			{
				bb.setVisible(true);
				setEnabled(false);
				builderLocked = true;
			update();
			}
		}
		
	}
	 
	private void update()
	{
		//change button highlight based on build lock
		if (builderLocked)
		{
			bb.setVisible(true);
			setEnabled(false);
		}
		else
		{
			bb.setVisible(false);
			setEnabled(true);
		}
	}
	 
	@Override
	public void setText(String name)
	{
		super.setText(name); text = name;
	}
	
	
	
	private BodyPasser nextBodyPasser = null, prevBodyPasser = null;
	private BodyButton nextBodyButton = null;
	
	public BodyButton getNext() { return nextBodyButton; }
	
	@Override 
	public void setNextBodyPasser(BodyPasser p)
	{
		nextBodyPasser = p;
		if (p instanceof BodyButton)
		{
			nextBodyButton = (BodyButton) p;
		}
	}
	
	@Override 
	public void setPrevBodyPasser(BodyPasser p)
	{
		prevBodyPasser = p;
	}
	
	
	@Override
	public void insertBefore(BodyPasser p)
	{
		if (p != null)
		{
			
			//forwards
			p.setNextBodyPasser( this );
			if (prevBodyPasser != null)
			{
				prevBodyPasser.setNextBodyPasser( p );
			}
			
			//backwards
			p.setPrevBodyPasser( prevBodyPasser );
			this.prevBodyPasser = p;
			
		}
		
		else
		{
			prevBodyPasser = null;
			prevBodyPasser.insertAfter(null);
		}
	}
	
	@Override
	public void insertAfter(BodyPasser p)
	{
		if (p != null)
		{
			
			//backwards
			if (nextBodyPasser != null)
			{
				nextBodyPasser.setPrevBodyPasser( p );
			}
			p.setPrevBodyPasser( this );
			
			//forwards
			p.setNextBodyPasser( nextBodyPasser );
			nextBodyPasser = p;
			
		}
		
		else
		{
			nextBodyPasser = null;
			nextBodyPasser.insertAfter(null);
		}
	}
	
	
	@Override
	public void remove()
	{
		if (prevBodyPasser != null)
		{
			prevBodyPasser.insertAfter(nextBodyPasser);
			prevBodyPasser = null;
			nextBodyPasser = null;
		}
		
		else if (nextBodyPasser != null)
		{
			nextBodyPasser.insertBefore(prevBodyPasser);
			prevBodyPasser = null;
			nextBodyPasser = null;
		}
	}
}
