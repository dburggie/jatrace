package jatrace.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyButton extends JButton
{
	//stuff
	
	public static enum Type { NULL };
	
	private String text = "";
	public Type type = Type.NULL;
	
	int width, height, parentWidth = 0, parentHeight = 0;
	int xpos, ypos;
	
	
	public MyButton(int x, int y, int w, int h, String message)
	{
		super(message);
		text = message;
		this.setLocation(x,y);
		this.setSize(w,h);
		type = Type.NULL;
	}
	
	public MyButton(int w, int h, String message)
	{
		super(message);
		text = message;
		this.setLocation(0,0);
		this.setSize(w,h);
		type = Type.NULL;
	}
	
	public void setType(Type t)
	{
		type = t;
	}
	
	@Override
	public void setText(String t)
	{
		super.setText(t);
		text = t;
	}
	
	@Override
	public void setLocation(int x, int y)
	{
		super.setLocation(x,y);
		xpos = x;
		ypos = y;
	}
	
	@Override
	public void setSize(int w, int h)
	{
		super.setSize(w,h);
		width = w; height = h;
	}
	
	public void setParent(MyPanel p)
	{
		parentWidth = p.getWidth();
		parentHeight = p.getHeight();
	}
	
	public void click()
	{
		if (type == Type.NULL) this.setText("CLICKED");
	}
	
	public int getXposition() { return xpos; }
	public int getYposition() { return ypos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public String getText() { return text; }
	
	
}
