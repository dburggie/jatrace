package jatrace.gui.builders;


import jatrace.*;
import jatrace.gui.*;
import jatrace.bodies.Sphere;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SphereBuilder extends ScrollableGridPanel
{
	VectorBuilder position;
	DoubleBuilder radius;
	ColorBuilder color;
	JButton expand;
	boolean expanded;
	DoubleBuilder reflectivity;
	DoubleBuilder specularity;
	BooleanBuilder matte;
	
	Sphere sphere;
	
	public SphereBuilder()
	{
		super();
		
		sphere = new Sphere();
		
		radius = new DoubleBuilder("Radius:",0.0);
		add(radius);
		
		addLabel("Center:");
		position = new VectorBuilder();
		add(position);
		
		addLabel("Color:");
		color = new ColorBuilder();
		add(color);
		
		reflectivity = new DoubleBuilder("Reflectivity:",0.3);
		add(reflectivity);
		
		specularity = new DoubleBuilder("Specularity:",10.0);
		add(specularity);
		
		matte = new BooleanBuilder("Matte:", false);
		add(matte);
		
		setOpaque(true);
	}
	
	private void addLabel(String text)
	{
		
		JLabel label = new JLabel(text, JLabel.CENTER);
		label.setPreferredSize( new Dimension(0,25) );
		add(label);
		
	}
	
	public Sphere build()
	{
		
		
		double rad = 0.0;
		double ref = 0.0;
		double spc = 0.0;
		
		jatrace.Vector v = position.build();
		jatrace.Color c = color.build();
		
		try {
			rad = radius.getValue();
			ref = reflectivity.getValue();
			spc = specularity.getValue();
		}
		
		catch (NumberFormatException e)
		{
			return null;
		}
		
		sphere.setPosition(v);
		sphere.setColor(c);
		sphere.setRadius(rad);
		sphere.setReflectivity(ref);
		sphere.setSpecularity(spc);
		
		return sphere;
		
	}
}
