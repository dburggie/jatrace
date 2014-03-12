package jatrace.gui.builders;

import jatrace.gui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VectorBuilder extends JPanel// implements FocusListener
{
	
	//private JTextField xField, yField, zField;
	private DoubleBuilder xField, yField, zField;
	private jatrace.Vector vector;
	
	JLabel label = null;
	JPanel lowerpanel = null;
	
	public jatrace.Vector build()
	{
		
		double x,y,z;
		
		//get X value
		try
		{
			x = xField.getValue();
		}
		
		catch (NumberFormatException e)
		{
			System.out.println("couldn't read input");
			xField.requestFocus();
			return null;
		}
		
		//get Y value
		try
		{
			y = yField.getValue();
		}
		
		catch (NumberFormatException e)
		{
			System.out.println("couldn't read input");
			yField.requestFocus();
			return null;
		}
		
		//get Z value
		try
		{
			z = zField.getValue();
		}
		
		catch (NumberFormatException e)
		{
			System.out.println("couldn't read input");
			zField.requestFocus();
			return null;
		}
		
		return vector.setxyz(x,y,z);
		
	}
	
	public VectorBuilder(String vectorName)
	{
		super(new GridLayout(2,1,0,2));
		
		if (vectorName == null || vectorName.equals(""))
		{
			vectorName = "Vector";
		}
		
		JLabel label = new JLabel(vectorName, JLabel.CENTER);
		label.setPreferredSize(new Dimension(0,25));
		add(label);
		
		vector = new jatrace.Vector(0.0,0.0,0.0);
		
		buildFromVector(vector);
		
		setOpaque(true);
	}
	
	private void buildFromVector(jatrace.Vector v)
	{
		if (lowerpanel != null)
		{
			remove(lowerpanel);
		}
		
		lowerpanel = new JPanel(new GridLayout(1,3));

		xField = new DoubleBuilder("x:",v.getX());
		lowerpanel.add(xField);

		yField = new DoubleBuilder("y:",v.getY());
		lowerpanel.add(yField);
		
		zField = new DoubleBuilder("z:",v.getZ());
		lowerpanel.add(zField);
		
		add(lowerpanel);
		
	}
	
	public void setText(String t)
	{
		label.setText(t);
	}
	
}
