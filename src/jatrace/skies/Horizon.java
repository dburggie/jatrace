package jatrace.skies;

import jatrace.*;

public class Horizon implements Sky
{
	protected Vect light;
	protected Color color;
	protected final double exp = 20.0;
	protected final Vect up = new Vect(0.0,1.0,0.0);
	
	public Horizon()
	{
		light = new Vect(0.0,1.0,0.0);
		color = new Color(0.2,0.2,0.9);
	}
	
	public Horizon( Vect l, Color c )
	{
		light = l.dup().norm();
		color = c.dup();
	}
	
	@Override
	public Vect [] getLight()
	{
		Vect l[] = { light.dup().delta(0.01) };
		return l;
	}
	
	@Override
	public Color getColor(Vect direction)
	{
		double d = Math.max(0.0, Math.max( direction.dot(light), 1 - direction.dot(up) ) );
		return color.dup().gamma( 1 - Math.pow(d, exp) );
	}
}
