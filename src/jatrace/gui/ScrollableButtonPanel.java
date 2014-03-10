package jatrace.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScrollableButtonPanel extends JPanel implements Scrollable
{
	
	private static Dimension preferredSize = new Dimension(200,0);
	
	BodyButton head = null;
	
	
	public ScrollableButtonPanel(BodyButton b)
	{
		super( new GridLayout(0,1,5,0) );
		head = b;
		while (b != null)
		{
			add(b);
			b = b.getNext();
		}
	}
	
	public void add(BodyButton b)
	{
		//super.add(Box.createRigidArea( new Dimension(0,25) ) );
		super.add(b);
	}
	
	@Override
	public boolean getScrollableTracksViewportHeight() { return false; }
	@Override
	public boolean getScrollableTracksViewportWidth() { return true; }
	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return preferredSize;
	}
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, 
			int orientation, int direction)
	{
		return 30;
		/*
		int step = 5;
		
		if (orientation == SwingConstants.VERTICAL)
		{
			
			step = (visibleRect.y % 50);
			
			if (direction > 1)
			{
				step = 50 - step;
			}
		}
		
		if (step == 0)
			step = 50;
		
		return step;
		*/
	}
	
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction)
	{
		return 30;
		/*
		int step = 5;
		
		if (orientation == SwingConstants.VERTICAL)
		{
			
			step = (visibleRect.y % 25);
			
			if (direction > 1)
			{
				step = 25 - step;
			}
		}
		
		if (step == 0)
			step = 25;
		
		return step;
		*/
	}
}
