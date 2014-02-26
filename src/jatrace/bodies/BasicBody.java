package jatrace.bodies;

import jatrace.*;

public class BasicBody implements Body
{
	public BasicBody() { this.setDefaults(); }

	protected BasicBody setDefaults()
	{
		this.setPosition( new Vect(0.0,0.0,0.0) );
		this.setOrientation( new Vect(0.0,1.0,0.0) );
		this.setReflectivity(0.2);
		this.setColor(new Color(0.3,0.1,0.1));
		this.setOpacity(1.0);
		this.setIndex(3.0);
		this.setSpecularity(10.0);
		this.setMatte(false);
		return this;
	}
	
	//dealing with position
	protected Vect position;
	public BasicBody setPosition(Vect p) { position = p.dup(); return this; }
	
	//dealing with orientation
	protected Vect orientation;
	public BasicBody setOrientation(Vect o) { orientation = o.dup(); return this; }
	
	// dealing with specularity
	protected double specularity;
	public BasicBody setSpecularity(double s) { specularity = s;	return this; }
	@Override public double getSpecularity(Vect point) { return specularity; }
	
	//dealing with color
	protected Color color;
	public BasicBody setColor(Color c) { color = c.dup(); return this; }
	@Override public Color getColor(Vect point)	{ return color.dup(); }
	
	//dealing with normal
	@Override public Vect getNormal(Vect point)	{ return orientation.dup(); }
	
	//dealing with reflectivity
	protected double reflectivity;
	public BasicBody setReflectivity(double r) { reflectivity = r; return this; }
	@Override public double getReflectivity(Vect point) { return reflectivity; }
	
	//dealing with opacity
	protected double opacity;
	public BasicBody setOpacity(double o) { opacity = o; return this; }
	@Override public double getOpacity(Vect point) { return opacity; }
	
	// dealing with index of refraction
	protected double index;
	public BasicBody setIndex(double i) { index = i; return this; }
	@Override public double getIndex(Vect point) { return index; }
	
	//dealing with matte vs specular reflection
	private boolean matte;
	public boolean setMatte(boolean m) { matte = m; return matte; }
	@Override public boolean isMatte(Vect point) { return matte; }
	
	//intersections
	@Override public double intersection(Ray ray) { return -1.0; }
}
