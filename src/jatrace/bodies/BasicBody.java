package jatrace.bodies;

import jatrace.*;

/** This class is not meant to be used for rendering, but rather to provide a
 * basic implementation of the Body interface. */
public class BasicBody implements Body
{
	public BasicBody() { this.setDefaults(); }

    /** Provides default settings to calling object. */
	protected BasicBody setDefaults()
	{
		this.setPosition( new Vector(0.0,0.0,0.0) );
		this.setOrientation( new Vector(0.0,1.0,0.0) );
		this.setReflectivity(0.2);
		this.setColor(new Color(0.3,0.1,0.1));
		this.setSpecularity(10.0);
		this.setMatte(false);
/*		this.setOpacity(1.0);
		this.setIndex(3.0); */
		return this;
	}
	
	//dealing with position
	protected Vector position;
    /** Sets body position. */
	public BasicBody setPosition(Vector p) { position = p.dup(); return this; }
	
	//dealing with orientation
	protected Vector orientation;

    /** Sets body orientation */
	public BasicBody setOrientation(Vector o) { orientation = o.dup(); return this; }
	
	// dealing with specularity
	protected double specularity;
    /** Sets body specularity, a measure of how rough a matte surface is:
     * higher values will provide a more distinct specular highlight on the
     * body. */
	public BasicBody setSpecularity(double s) { specularity = s;	return this; }
	@Override public double getSpecularity(Vector point) { return specularity; }
	
	//dealing with color
	protected Color color;
    /** Sets the color of the calling body (for monochrome bodies). */
	public BasicBody setColor(Color c) { color = c.dup(); return this; }
    /** Gets the color of the body at the given point. */
	@Override public Color getColor(Vector point)	{ return color.dup(); }
	
	//dealing with normal
    /** Gets the unit vector perpendicular to the body surface at the given
     * point. */
	@Override public Vector getNormal(Vector point)	{ return orientation.dup(); }
	
	//dealing with reflectivity
	protected double reflectivity;
    /** Sets body base reflectivity (for uniformly reflective non-matte bodies. */
	public BasicBody setReflectivity(double r) { reflectivity = r; return this; }
	@Override public double getReflectivity(Vector point) { return reflectivity; }
	
	//dealing with matte vs specular reflection
	private boolean matte;
    /** Sets the matte flag (for uniformly matte bodies). */
	public boolean setMatte(boolean m) { matte = m; return matte; }
    /** Returns whether the body is matte or not at the given point. */
	@Override public boolean isMatte(Vector point) { return matte; }
	
	//intersections
    /** Returns the distance along the ray to the first intersection with the
     * body, any negative value implies no intersection in the positive
     * direction. */
	@Override public double intersection(Ray ray) { return -1.0; }
	
	/*
	//dealing with opacity
	protected double opacity;
	public BasicBody setOpacity(double o) { opacity = o; return this; }
	@Override public double getOpacity(Vector point) { return opacity; }
	
	// dealing with index of refraction
	protected double index;
	public BasicBody setIndex(double i) { index = i; return this; }
	@Override public double getIndex(Vector point) { return index; }
	*/
}
