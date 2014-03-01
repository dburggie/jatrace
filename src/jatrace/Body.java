package jatrace;


/**	Provides a standard interface for World to interact with Body */
public interface Body
{
	/** Gets color of object at a given point. */
	public Color getColor(Vector point);
	
	/** Gets vector normal to Body at given point. */
	public Vector getNormal(Vector point);
	
	/** Returns base amount of light reflected from surface at a point. 
	 *  Must return a value between 0 and 1. */
	public double getReflectivity(Vector point);
	
	/** For matte surfaces, returns a gamma exponent for creating specular
	 *  highlights. A value of 0.0 will always return white, a value of 1.0
	 *  will not highlight the surface at all. Weird Stuff (tm) might happen
	 *  if values greater than 1.0 are returned. */
	public double getSpecularity(Vector point);
	
	/** Returns a simple boolean value: Is this surface matte at this point? */
	public boolean isMatte(Vector point);
	
	/** Returns a positive distance along ray to a point of intersection with
	 *  the body ONLY IF an intersection occurs. Any negative value returned
	 *  implies no intersection. However, if no intersection occurs, it is
	 *  standard to return -1.0. */
	public double intersection(Ray ray);
	
	// As yet unimplemented.
	/* 
	public double getOpacity(Vector point);
	public double getIndex(Vector point);  
	*/
}
