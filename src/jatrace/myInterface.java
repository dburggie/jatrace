package raytrace;

import raytrace.threeD.Vect;
import raytrace.threeD.Ray;
import raytrace.threeD.bodies.Body;

private class myInterface
{
	public final boolean isMatte;
	public final double exp, distance;
	public final Vect poi, normal;
	public final Body body;
	public final Color color;
	
	public myInterface()
	{
		distance = null;
		poi = null;
		normal = null;
		body = null;
		color = null;
		isMatte = false;
		exp = null;
	}
	
	public myInterface(double d, Vect p, Vect n, Body b, Color c)
	{
		distance = d;
		poi = p;
		normal = n;
		body = b;
		color = c;
		if (body)
		{
			isMatte = body.isMatte();
			exp = body.specularity();
		}
		else
		{
			isMatte = false;
			exp = null;
		}
	}
	
	public myInterface dup()
	{
		return new myInterface(distance, poi, normal, body, color);
	}
	
	public myInterface reset()
	{
		distance = null;
		poi = null;
		normal = null;
		body = null;
		color = null;
		isMatte = false;
		exp = null;
		return this;
	}
	
	public myInterface hit(Body b, double d)
	{
		if (!b)
		{
			body = b;
			distance = d;
		}
		else if (d < distance)
		{
			body = b;
			distance = d;
		}
		return this;
	}
	
	public myInterface registerHit(Ray r)
	{
		if (!b && d > 0.0)
		{
			poi = r.follow(distance);
			normal = body.normal(poi);
			color = body.getColor(poi);
			exp = body.specularity();
			isMatte = body.isMatte();
		}
		
		return this;
	}
}
