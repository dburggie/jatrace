import jatrace.*;
import jatrace.bodies.*;
import jatrace.skies.*;

public class testTracer
{
	public static void main(String [] args)
	{
		Camera c = new Camera();
		c.setPPU(10);
		c.setPosition( new Vect(0.0,0.0,10.0) );
		c.setFocus( new Vect(0.0,0.0,0.0) );
		
		Sphere s = new Sphere( new Vect(0.0,0.0,0.0), 1.0, new Color( 0.9,0.3,0.3) );
		s.setReflectivity(0.1);
		
		Sphere [] bodies = new Sphere [1];
		bodies[0] = s;
		
		Bluesky bs = new Bluesky();
		
		World w = new World(bodies,bs);
		
		Ray r = new Ray( new Vect(0.0,1.0,0.0), new Vect(0.0,-1.0,0.0));
		
		Color color = s.getColor(new Vect());
		int [] samples = color.p();
		System.out.println("red: " + samples[0] + " green: " + samples[1] + " blue: " + samples[2]);
		
		color = w.sample(r,0);
		samples = color.p();
		System.out.println("red: " + samples[0] + " green: " + samples[1] + " blue: " + samples[2]);
		
//		Tracer t =  new Tracer(new World(bodies, bs), c);
//		t.draw(1).write();
	}
}
