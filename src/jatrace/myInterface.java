package jatrace;

final class myInterface
{
	public boolean isMatte;
	public double exp, distance, reflectivity;
	public Vect poi, normal;
	public Body body;
	public Color color;
	
	public myInterface()
	{
		distance = -1.0;
		poi = null;
		normal = null;
		body = null;
		color = null;
		isMatte = false;
		exp = 1.0;
		reflectivity = 0.0;
	}
	
	public myInterface(double d, Vect p, Vect n, Body b, Color c)
	{
		distance = d;
		poi = p;
		normal = n;
		body = b;
		color = c;
		if (body != null)
		{
			isMatte = body.isMatte(poi);
			exp = body.getSpecularity(poi);
			reflectivity = body.getReflectivity(poi);
		}
		else
		{
			isMatte = false;
			exp = 1.0;
			reflectivity = 0.0;
		}
	}
	
	public myInterface dup()
	{
		return new myInterface(distance, poi, normal, body, color);
	}
	
	public myInterface reset()
	{
		distance = -1.0;
		poi = null;
		normal = null;
		body = null;
		color = null;
		isMatte = false;
		exp = 1.0;
		reflectivity = 0.0;
		return this;
	}
	
	public myInterface hit(Body b, double d)
	{
		if (body == null)
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
		if (body != null && distance > 0.0)
		{
			poi = r.follow(distance);
			normal = body.getNormal(poi);
			color = body.getColor(poi);
			exp = body.getSpecularity(poi);
			reflectivity = body.getReflectivity(poi);
			isMatte = body.isMatte(poi);
		}
		
		return this;
	}
}
