package jatrace.bodies;

import jatrace.*;

/** Describes a mathematical plane, a surface that is infinitely wide with
 *  universal orientation. */
public class Plane extends BasicBody
{
	
	/** Sets all the default parameters of a plane. These are: Position 
	 *  (0.0,0.0,0.0); Normal (0.0,1.0,0.0); Reflectivity 0.2; Color gray; */	
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
	
	/** Instantiates a plane with given positin, normal, and color. */	
	public Plane(Vector p, Vector n, Color c)
	{
		this.setDefaults();
		this.setPosition(p);
		this.setNormal(n);
		this.setColor(c);
	}
	
	/** Instantiates a default plane (gray, at origin, pointing up). */	
	public Plane()
	{
		this.setDefaults();
	}
	
	protected Vector normal;
	/** Sets surface orientation by the vector normal to the plane. */	
	public Plane setNormal(Vector n)
	{
		normal = n.dup().norm();
		return this;
	}
	
	/** Gets the surface normal at the given point. */	
	@Override public Vector getNormal(Vector point)
	{
		return normal.dup();
	}
	
	/** Gets distance along ray to intersection with plane. */	
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
    	
    	// return ratio of magnitudes: 
    	//(distance between plane and ray) / (speed ray approaches plane)
    	return projS / projD;
    	
	}
}
