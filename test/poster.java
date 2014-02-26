/*
win_w = 3.5
win_h = 3.0
ppu = 100
passes = 2
filename = 'Poster-{0}x{1}.png'.format(int(win_w * ppu), int(win_h * ppu))

ball = Sphere(Vector(0.0,0.0,0.0), 1.0, Color(0.1,0.1,0.1))
ball.set_reflectivity(0.6)

floorN = Vector(0.0,1.0,0.0)
floorP = Vector(0.0,0.0,0.0)
floorO = Vector(1.0,0.0,0.0)

floor = CheckPlane(floorN, floorP, floorO)
floor.set_reflectivity(0.4)

bodies = [ball, floor]
world = World(bodies, skies.Horizon()).set_base_brightness(0.8)

camP = Vector(5.0,1.0,5.0)
camF = Vector(0.0,1.0,0.0)
cam = Camera(camP, camF, win_w, win_h)

Tracer(world, cam).draw(passes).write(filename)
*/

import jatrace.*;
import jatrace.skies.*;
import jatrace.bodies.*;

public class poster
{
	public static main(String [] args)
	{
		
		double	win_w = 3.5,
				win_h = 3.0;
		
		int 	ppu = 100, 
				passes = 2,
				
				pix_w = (int) (win_w * ppu),
				pix_h = (int) (win_h * ppu);
		
		String	filename = "Poster-" + pix_w + "x" + pix_h + ".png";
		
		Sphere	Ball = new Sphere( new Vect(0.0,0.0,0.0), 1.0, Color(0.1,0.1,0.1));
		Ball.setReflectivity(0.6);
		
		Vect	floorN = new Vect(0.0,1.0,0.0),
				floorP = new Vect(0.0,0.0,0.0),
				floorO = new Vect(1.0,0.0,0.0);
		
		CheckPlane Floor = new CheckPlane(floorN, floorP, floorO);
		Floor.setReflectivity(0.4);
		
		World world = new World().setSky(new Horizon());
		world.addBody(Floor).addBody(Ball);
		
		Vect	camP = new Vect(5.0, 1.0, 5.0),
				camF = new Vect(0.0,1.0,0.0);
		
		Camera cam = new Camera(camP, camF, win_w, win_h);
		
		Tracer t = new Tracer(world, cam);
		t.draw(passes).write(filename);
		
	}
}
