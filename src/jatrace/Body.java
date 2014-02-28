package jatrace;

public interface Body
{
	public Color getColor(Vector point);
	public Vector getNormal(Vector point);
	public double getReflectivity(Vector point);
	public double getOpacity(Vector point);
	public double getIndex(Vector point);
	public double getSpecularity(Vector point);
	public boolean isMatte(Vector point);
	public double intersection(Ray ray);
	
	
}
