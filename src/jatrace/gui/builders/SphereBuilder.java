package jatrace.gui.builders;


import jatrace.*;
import jatrace.gui.*;
import jatrace.bodies.Sphere;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SphereBuilder extends JPanel
{
	VectorBuilder position;
	DoubleBuilder radius;
	ColorBuilder color;
	
	public SphereBuilder()
	{
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		radius = new DoubleBuilder("Radius:",0.0);
		add(radius);
		
		position = new VectorBuilder("Center:");
		add(position);
		
		color = new ColorBuilder("Color:");
		add(color);
		
		setOpaque(true);
	}
	
	public Sphere build()
	{
		
		jatrace.Vector v = position.build();
		double r = radius.getValue();
		jatrace.Color c = color.build();
		
		return new Sphere(v,r,c);
		
	}
}
