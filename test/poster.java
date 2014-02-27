import jatrace.*;
import jatrace.skies.*;
import jatrace.bodies.*;

public class poster
{
	public static void main(String [] args)
	{
		
		double	win_w = 3.5,
				win_h = 3.0;
		
		int 	ppu = 200, 
				passes = 32,
				
				pix_w = (int) (win_w * ppu),
				pix_h = (int) (win_h * ppu);
		
		String	filename = "Poster-" + pix_w + "x" + pix_h + ".png";
		
		double	radius = 0.8, smallr = 1.0 - radius;
		
		//setup world and sky
		World world = new World().setSky(new Horizon( new Vect(1.0,1.0,1.0), new Color(0.01,0.01,0.01) ) );
		world.setBaseBrightness(0.4);
		
		//setup floor
		Vect	floorN = new Vect(0.0,1.0,0.0),
				floorP = new Vect(0.0,0.0,0.0),
				floorO = new Vect(1.0,0.0,0.0);
		CheckPlane Floor = new CheckPlane(floorP, floorN, floorO);
		Floor.setReflectivity(0.3);
		world.addBody(Floor);
		
		//setup primary sphere
		Sphere primary = new Sphere( new Vect(0.0,radius,0.0), radius, new Color(0.01,0.01,0.01));
		primary.setReflectivity(0.8);
		world.addBody(primary);
		
		//setup centers
		Vect [] centers =
		{
			new Vect( 1.0,0.0, 0.0).norm().trans(0.0,smallr,0.0),
			new Vect( 1.0,0.0, 1.0).norm().trans(0.0,smallr,0.0),
			new Vect( 1.0,0.0,-1.0).norm().trans(0.0,smallr,0.0),
			new Vect( 0.0,0.0, 1.0).norm().trans(0.0,smallr,0.0),
			new Vect( 0.0,0.0,-1.0).norm().trans(0.0,smallr,0.0),
			new Vect(-1.0,0.0, 1.0).norm().trans(0.0,smallr,0.0),
			new Vect(-1.0,0.0, 0.0).norm().trans(0.0,smallr,0.0),
			new Vect(-1.0,0.0,-1.0).norm().trans(0.0,smallr,0.0)
		};
		Sphere s;
		Color sColor = new Color(0.3,0.1,0.1);
		for (Vect v : centers)
		{
			s = new Sphere(v, smallr, sColor.dup());
			s.setReflectivity(0.4);
			world.addBody(s);
		}
		
		
		Vect	camP = new Vect(2.0,0.6,10.0),
				camF = new Vect(0.0,0.5,0.0),
				camU = new Vect(0.0,1.0,0.0);
		
		Camera cam = new Camera(camP, camF, camU, win_w, win_h, ppu);
		Tracer t = new Tracer(world, cam);
		
		t.draw(passes).write(filename);
		
	}
}
