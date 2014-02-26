package jatrace.bodies;

import jatrace.*;
import jatrace.bodies.Plane;

public class Plane extends BasicBody
{
	@Override public Plane setDefaults()
	{
		this.setPosition( new Vect(0.0,0.0,0.0) );
		this.setOrientation( new Vect(1.0,0.0,0.0) );
		this.setNormal( new Vect(0.0,1.0,0.0) );
		this.setReflectivity(0.2);
		this.setColor(new Color(0.3,0.3,0.3));
		this.setOpacity(1.0);
		this.setIndex(3.0);
		this.setSpecularity(10.0);
		this.setMatte(false);
		return this;
	}
	
	public Plane(Vect p, Vect n, Color c)
	{
		this.setDefaults();
		this.setPosition(p);
		this.setNormal(n);
		this.setColor(c);
	}
	
	public Plane()
	{
		this.setDefaults();
	}
	
	protected Vect normal;
	public Plane setNormal(Vect n)
	{
		normal = n.dup().norm();
		return this;
	}
	@Override public Vect getNormal(Vect point)
	{
		return normal.dup();
	}
	
	@Override
	public double intersection(Ray ray)
	{
		
		// check if ray direction is parallel to plane
    	double projD = normal.dot(ray.d());
    	if (Math.abs(projD) < 0.0001)
    	{
    		return -1.0;
    	}
    	
    	// make sure ray is going towards plane
    	double projS = position.sub(ray.o()).dot(normal);
    	if (projD * projS < 0.0)
    	{
    		return -1.0;
    	}
    	
    	// return ratio of magnitudes (distance between plane and ray) / (speed ray approaches plane)
    	return projS / projD;
    	
	}
}
