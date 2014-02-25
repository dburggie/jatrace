package raytrace;

import raytrace.threeD.Vector;
import raytrace.threeD.Ray;
import raytrace.threeD.Color;
import raytrace.threeD.bodies.Body;
import raytrace.threeD.skies.Sky;

public class World
{
	protected Body bodies[];
	protected Sky sky;
	protected Vect lights[];
	protected Interface i;
	protected double baseBrightness;
	
	public World(Body b[], Sky s)
	{
		bodies = b;
		sky = s;
		i = new Interface;
		lights = s.getLights();
		baseBrightness = 0.2;
	}
	
	
	
}
