package jatrace.bodies;

import jatrace.*;

public class WhitePlane extends Plane
{
	
	Color TrueColor;
	
	@Override public Plane setDefaults()
	{
		this.setPosition( new Vector(0.0,0.0,0.0) );
		this.setNormal( new Vector(0.0,1.0,0.0) );
		this.setOrientation( new Vector(1.0,0.0,0.0) );
		this.setReflectivity(0.2);
		this.setColor(new Color(0.3,0.3,0.3));
		this.setSpecularity(10.0);
		this.setMatte(false);
/*		this.setOpacity(1.0);
		this.setIndex(3.0); */
		return this;
	}
	
	public WhitePlane(Vector p, Vector n, Color c)
	{
		this.setDefaults();
		this.setPosition(p);
		this.setNormal(n);
		this.setColor(c);
	}
	
	public WhitePlane()
	{
		this.setDefaults();
	}
	
	@Override
	public WhitePlane setColor(Color c)
	{
		super.setColor(c);
		TrueColor = c.dup();
	}
	
	@Override
	public Color getColor( Vector point )
	{
		
	}
	
	
}
