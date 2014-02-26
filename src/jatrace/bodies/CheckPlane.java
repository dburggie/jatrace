package jatrace.bodies;

import jatrace.*;
import jatrace.bodies.Plane;

public class CheckPlane extends Plane
{
	
	public CheckPlane( Vect n, Vect origin, Vect orientation )
	{
		this.setDefaults();
		this.setNormal(n);
		this.setPosition(origin);
		this.setOrientation(orientation);
		this.setColor( new Color(0.01,0.01,0.01), new Color(0.99,0.99,0.99) );
	}
	
	
	//dealing with orientation
	protected Vect oX, oY;
	@Override
	public CheckPlane setOrientation(Vect o)
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
	public Color getColor(Vect point)
	{
		Vect d = point.sub(position);
		
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
