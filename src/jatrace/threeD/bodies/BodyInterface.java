package jatrace.threeD.bodies;

import jatrace.threeD.*;

public interface BodyInterface
{
	public Color getColor(Vect point);
	public Vect getNormal(Vect point);
	public double getReflectivity(Vect point);
	public double getOpacity(Vect point);
	public double getIndex(Vect point);
	public double getSpecularity(Vect point);
	public boolean isMatte(Vect point);
	public double intersection(Ray ray);
	
	
}
