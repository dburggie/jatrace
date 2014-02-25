package raytrace;

import raytrace.threeD.Vect;
import raytrace.threeD.Ray;
import raytrace.threeD.bodies.Body;

private class Interface
{
	private boolean isMatte;
	private double exp, distance;
	private Vect poi, normal;
	private Body body;
	private Color color;
	
	public Interface()
	{
		distance = null;
		poi = null;
		normal = null;
		body = null;
		color = null;
		isMatte = false;
		exp = null;
	}
	
	public Interface(double d, Vect p, Vect n, Body b, Color c)
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
	
	public Interface dup()
	{
		return new Interface(distance, poi, normal, body, color);
	}
	
	public Interface reset()
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
	
	public Interface hit(Body b, double d)
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
	
	public Interface registerHit(Ray r)
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
