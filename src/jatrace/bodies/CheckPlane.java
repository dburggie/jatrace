package jatrace.bodies;

import jatrace.*;
import jatrace.bodies.Plane;

public class CheckPlane extends Plane
{
	
	public CheckPlane( Vector o, Vector n, Vector orientation )
	{
		this.setDefaults();
		this.setNormal(n);
		this.setPosition(o);
		this.setOrientation(orientation);
		this.setColor( new Color(0.01,0.01,0.01), new Color(0.99,0.99,0.99) );
	}
	
	
	//dealing with orientation
	protected Vector oX, oY;
	@Override
	public CheckPlane setOrientation(Vector o)
	{
		super.setOrientation(o);
		oX = o.sub(position).norm();
		oY = normal.cross(oX).norm();
		oX = oY.cross(normal).norm();
		return this;
	}
	
	
	//dealing with color
	protected Color c1, c2;
	public CheckPlane setColor(Color a, Color b) { c1 = a.dup(); c2 = b.dup(); return this; }
	@Override
	public Color getColor(Vector point)
	{
		Vector d = point.sub(position);
		
		double	lenx = Math.floor( d.dot(oX) ),
				leny = Math.floor( d.dot(oY) );
		
		//int ModularDistance = Math.abs( (int) (lenx + leny) ) % 2;
		if ( 0 == Math.abs( (int) (lenx + leny) ) % 2)
		{
			return c1.dup();
		}
		else
		{
			return c2.dup();
		}
	}
	
	
}
