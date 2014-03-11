package jatrace.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorBuilder extends JPanel
{
	
	private DoubleBuilder redField, greenField, blueField;
	private jatrace.Color color;
	
	JLabel label = null;
	JPanel lowerpanel = null;
	
	public jatrace.Color build()
	{
		
		double red, green, blue;
		
		//get red value
		try
		{
			red = redField.getValue(256.0);
		}
		
		catch (NumberFormatException e)
		{
			System.out.println("couldn't read input");
			redField.requestFocus();
			return null;
		}
		
		//get green value
		try
		{
			green = greenField.getValue(256.0);
		}
		
		catch (NumberFormatException e)
		{
			System.out.println("couldn't read input");
			greenField.requestFocus();
			return null;
		}
		
		//get blue value
		try
		{
			blue = blueField.getValue(256.0);
		}
		
		catch (NumberFormatException e)
		{
			System.out.println("couldn't read input");
			blueField.requestFocus();
			return null;
		}
		
		return color.setRGB(red, green, blue);
		
	}
	
	public ColorBuilder(String colorName)
	{
		super(new GridLayout(2,1,0,2));
		
		if (colorName == null || colorName.equals(""))
		{
			colorName = "Color";
		}
		
		JLabel label = new JLabel(colorName, JLabel.CENTER);
		label.setPreferredSize(new Dimension(0,25));
		add(label);
		
		color = new jatrace.Color();
		
		buildFromColor(color);
		
		setOpaque(true);
	}
	
	private void buildFromColor(jatrace.Color v)
	{
		if (lowerpanel != null)
		{
			remove(lowerpanel);
		}
		
		lowerpanel = new JPanel(new GridLayout(1,3));

		redField = new DoubleBuilder("r:",v.getRed());
		lowerpanel.add(redField);

		greenField = new DoubleBuilder("g:",v.getGreen());
		lowerpanel.add(greenField);
		
		blueField = new DoubleBuilder("b:",v.getBlue());
		lowerpanel.add(blueField);
		
		add(lowerpanel);
		
	}
	
	public void setText(String t)
	{
		label.setText(t);
	}
	
}
