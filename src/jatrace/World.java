package jatrace;

import jatrace.bodies.Sphere;
import jatrace.skies.Bluesky;

public class World
{
	protected linkedBody bodies;
	protected Sky sky;
	protected Vect lights[];
	protected myInterface i;
	protected double baseBrightness;
	
	public World()
	{
		i = new myInterface();
		baseBrightness = 0.2;
	}
	
	public World(Body b[], Sky s)
	{
		for ( Body x : b )
		{
			this.addBody(x);
		}
		this.setSky(s);
		i = new myInterface();
		baseBrightness = 0.2;
	}
	
	public World addBody(Body b)
	{
		bodies = new linkedBody(b);
		return this;
	}
	
	public World setSky(Sky s)
	{
		sky = s;
		return this;
	}
	
	public World getLights()
	{
		lights = sky.getLight();
		return this;
	}
	
	public World setBaseBrightness(double b)
	{
		assert (b >= 0.0 && b <= 1.0) : "Set base brightness out of bounds";
		baseBrightness = b;
		return this;
	}
	
	public myInterface trace(Ray ray, Body lastHit)
	{
		i.reset();
		linkedBody lb = linkedBody.top();
		Body b;
		double distance;
		
		while (lb != null)
		{
			b = lb.b();
			distance = b.intersection(ray);
			if (distance > 0.0)
			{
				if (b != lastHit || distance > 0.00001)
				{
					i.hit(b,distance);
				}
			}
			lb = lb.next();
		}
		
		return i.registerHit(ray);
	}
	
	public myInterface trace(Ray ray)
	{
		return this.trace(ray, null);
	}
	
	public double shade()
	{
		Vect poi = i.poi;
		Ray ray;
		linkedBody lb;
		Body b;
		
		int numLights = lights.length;
		double	distance = -1.0,
				brightness = 0.0, 
				lambertian = 0.0, 
				brightnessPerLight = 1.0 / numLights;
		boolean hitSomething;
		
		for (Vect L : lights)
		{
			lambertian = L.dot(i.normal);
			if (lambertian > 0.0)
			{
				hitSomething = false;
				ray = new Ray(i.poi, L);
				lb = linkedBody.top();
				while (lb != null)
				{
					b = lb.b();
					lb = lb.next();
					distance = b.intersection(ray);
					
					if (distance > 0.0 && (b != i.body || distance > 0.00001))
					{
						lb = null;
						hitSomething = true;
					}
				}
				if (!hitSomething)
				{
					brightness += brightnessPerLight * lambertian;
				}
			}
		}
		
		brightness = Math.max(brightness, baseBrightness);
		return Math.min(1.0, brightness);
	}
	
	
	public Color highlight(double lux, Ray ray)
	{
		lux = Math.max(lux, baseBrightness);
		
		double highlight = 0.0;
		Vect d = ray.reflect(i.poi, i.normal).d();
		for (Vect L : lights)
		{
			highlight = Math.max(highlight, d.dot(L));
		}
		
		highlight = Math.pow(highlight, i.exp);
		return i.color.dim(lux).gamma(1 - highlight);
	}
	
	public Color sample(Ray ray, int depth, Body lastHit)
	{
		
		this.trace(ray,lastHit);
		
		//return sky color if we didn't hit anything
		if (i.body == null)
		{
			return sky.getColor(ray.d());
		}
		
		//find surface illumnation
		this.getLights();
		double lux = this.shade();
		
		// return a specularly highlighted color for matte object
		if (i.isMatte)
		{
			return this.highlight(lux, ray);
		}
		
		//handle depth
		if (depth == 0)
		{
			return i.color.dim(lux);
		} 
		else
		{
			double cosine = Math.abs(ray.d().dot(i.normal));
			
			double R = i.body.getReflectivity(i.poi);
			double D = 1.0 - R;
			double sPower = R + D * Math.pow(1.0 - cosine, i.exp);
			double tPower = 1.0 - sPower;
			
			ray.reflect(i.poi,i.normal);
			
			Color tColor = i.color.dim(lux).dim(tPower);
			Color sColor = this.sample(ray, depth - 1, i.body).dim(sPower);
			
			return tColor.add(sColor);
		}
	}
	
	
	public Color sample(Ray ray, int depth)
	{
		return this.sample(ray, depth, null);
	}
	
	public Color sample(Ray ray)
	{
		return this.sample(ray, 8, null);
	}
}
