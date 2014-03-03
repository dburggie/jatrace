package jatrace.skies;

import jatrace.*;

public class White implements Sky
{
	protected Vector sun;
	protected final Color color = new Color(0.99999,0.99999,0.99999);
	
	public White()
	{
		sun = new Vector(0.0,1.0,0.0);
	}
	
	@Override
	public Color getColor(Vector direction)
	{
		double gamma = Math.pow(1 - direction.dot(sun), 99.0);
		return color.dup().gamma(gamma);
	}
	
	@Override
	public Vector [] getLight()
	{
		Vector [] L = { new Vector(0.0,1.0,0.0) };
		return L;
	}
}
