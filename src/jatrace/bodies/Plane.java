package jatrace.bodies;

import jatrace.*;

public class Plane extends BasicBody
{
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
	
	public Plane(Vector p, Vector n, Color c)
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
	
	protected Vector normal;
	public Plane setNormal(Vector n)
	{
		normal = n.dup().norm();
		return this;
	}
	@Override public Vector getNormal(Vector point)
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
