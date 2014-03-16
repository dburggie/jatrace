package jatrace.gui.builders;


import jatrace.*;
import jatrace.gui.*;
import jatrace.bodies.Sphere;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SphereBuilder extends ScrollableGridPanel implements ActionListener
{
	VectorBuilder position;
	DoubleBuilder radius;
	ColorBuilder color;
	JButton expand;
	boolean expanded;
	DoubleBuilder reflectivity;
	DoubleBuilder specularity;
	BooleanBuilder matte;
	
	public SphereBuilder()
	{
		super();
		
		radius = new DoubleBuilder("Radius:",0.0);
		add(radius);
		
		position = new VectorBuilder("Center:");
		add(position);
		
		color = new ColorBuilder("Color:");
		add(color);
		
		expand = new JButton("Advanced Options");
		expand.setPreferredSize(new Dimension(0,25));
		add(expand);
		expanded = false;
		
		reflectivity = new DoubleBuilder("Reflectivity:",0.3);
		specularity = new DoubleBuilder("Specularity:",10.0);
		matte = new BooleanBuilder("Matte:", false);
		
		
		setOpaque(true);
	}
	
	public Sphere build()
	{
		
		jatrace.Vector v = position.build();
		double r = radius.getValue();
		jatrace.Color c = color.build();
		
		return new Sphere(v,r,c);
		
	}
	
	private void showAdvancedOptions()
	{
		
		if (!expanded)
		{
			remove(expand);
			add(reflectivity);
			add(specularity);
			add(matte);
			revalidate();
			expanded = true;
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if (!expanded && e.getSource() == expand)
		{
			showAdvancedOptions();
		}
		
	}
}
