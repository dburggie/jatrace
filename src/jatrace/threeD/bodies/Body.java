package jatrace.threeD.bodies;

import jatrace.threeD.Ray;
import jatrace.threeD.Color;
import jatrace.threeD.Vect;

public class Body implements BodyInterface
{
	public Body() { this.setDefaults(); }

	protected Body setDefaults()
	{
		this.setPosition( new Vect(0.0,0.0,0.0) );
		this.setOrientation( new Vect(0.0,1.0,0.0) );
		this.setColor(new Color(0.3,0.1,0.1));
		this.setOpacity(1.0);
		this.setIndex(3.0);
		this.setSpecularity(10.0);
		this.setMatte(false);
		return this;
	}
	
	//dealing with position
	protected Vect position;
	public Body setPosition(Vect p) { position = p.dup(); return this; }
	
	//dealing with orientation
	protected Vect orientation;
	public Body setOrientation(Vect o) { orientation = o.dup(); return this; }
	
	// dealing with specularity
	protected double specularity;
	public Body setSpecularity(double s) { specularity = s;	return this; }
	@Override public double getSpecularity(Vect point) { return specularity; }
	
	//dealing with color
	protected Color color;
	public Body setColor(Color c) { color = c.dup(); return this; }
	@Override public Color getColor(Vect point)	{ return color.dup(); }
	
	//dealing with normal
	@Override public Vect getNormal(Vect point)	{ return orientation.dup(); }
	
	//dealing with reflectivity
	protected double reflectivity;
	public Body setReflectivity(double r) { reflectivity = r; return this; }
	@Override public double getReflectivity(Vect point) { return reflectivity; }
	
	//dealing with opacity
	protected double opacity;
	public Body setOpacity(double o) { opacity = o; return this; }
	@Override public double getOpacity(Vect point) { return opacity; }
	
	// dealing with index of refraction
	protected double index;
	public Body setIndex(double i) { index = i; return this; }
	@Override public double getIndex(Vect point) { return index; }
	
	//dealing with matte vs specular reflection
	private boolean matte;
	public boolean setMatte(boolean m) { matte = m; return matte; }
	@Override public boolean isMatte(Vect point) { return matte; }
	
	//intersections
	@Override public double intersection(Ray ray) { return -1.0; }
}
