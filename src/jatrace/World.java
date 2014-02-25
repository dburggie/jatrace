package raytrace;

import raytrace.threeD.Vector;
import raytrace.threeD.Ray;
import raytrace.threeD.Color;
import raytrace.threeD.bodies.Body;
import raytrace.threeD.skies.Sky;

public class World
{
	protected linkedBody bodies;
	protected Sky sky;
	protected Vect lights[];
	protected myInterface i;
	protected double baseBrightness;
	
	public World(Body b[], Sky s)
	{
		for ( Body x : b )
		{
			bodies = new linkedBody(x);
		}
		sky = s;
		i = new Interface;
		lights = s.getLights();
		baseBrightness = 0.2;
	}
	
	
	
}
