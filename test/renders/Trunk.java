package renders;

import jatrace.*;
import jatrace.bodies.*;
import jatrace.skies.*;

public class Trunk
{
	
	protected CheckPlane floor;
	protected Sphere sphere;
	//protected TruncatedSphere plate;
	
	protected double delta;
	protected int ppu;
	protected int passes;
	
	
	public Trunk(double d, int pixels, int rays)
	{
		delta = d; ppu = pixels; passes = rays;
		
		//plate = new TruncatedSphere(
		//			new Vector(5.0,1.0,0.0), 
		//			1.0, 
		//			new Color(0.7,0.2,0.5) 
		//		);
		//plate.setCosine(0.1);
		//plate.setOrientation(new Vector(-1.0,0.0,0.0) );
		//plate.setReflectivity(0.7);
		
		sphere = new Sphere(new Vector(-5.0,1.0,0.0), 1.0, new Color(0.1,0.1,0.6) );
		
		floor = new CheckPlane( new Vector(0.0,0.0,0.0), new Vector(0.0,1.0,0.0), new Vector(1.0,0.0,0.0) );
		
		
	}
	
	public void render()
	{
		
		Camera cam = new Camera( new Vector(0.0,20.0,0.0), new Vector(5.0,1.0,0.0), 4.0,4.0);
		cam.setPPU(ppu);
		cam.setDelta(delta);
		
		World world = new World();
		world.setSky(new Bluesky(new Vector(-0.2,10.0,1.0)) );
		world.addBody(floor);
		world.addBody(sphere);
		//world.addBody(plate);
		
		Tracer tracer = new Tracer(world, cam);
		tracer.draw(passes).write("TrunkRender.png");
		
	}
	
	
	/** Acceptible command line arguments are field of view (double), pixels per
	 *  unit (int), and rays per pixel (int). */
	public static void main(String [] args)
	{
		
		
		double d = 0.0;
		int pixels = 100, rays = 8;
		
		try {
			if (args.length <= 0 || args.length > 3) { throw new Exception (); }
			if (args.length > 0) { d = Double.parseDouble(args[0]); }
			if (args.length > 1) { pixels = Integer.decode(args[1]); }
			if (args.length > 2) { rays = Integer.decode(args[2]); }
		}
		
		catch (Exception e) {
			d = 0.0;
			pixels = 100; rays = 8;
		}
		
		Trunk trunk = new Trunk(d, pixels, rays);
		
		trunk.render();
		
	}
	
}
