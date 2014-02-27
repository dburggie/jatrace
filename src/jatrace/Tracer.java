package jatrace;

public class Tracer
{
	protected World world;
	protected Camera camera;
	protected myImage image;
	protected int ppu, width, height;
	
	public Tracer(World w, Camera c)
	{
		this.setWorld(w);
		this.setCamera(c);
		this.image = null;
	}
	
	public Tracer()
	{
		this.setWorld();
		this.setCamera();
		this.image = null;
	}
	
	public Tracer setWorld()
	{
		return this.setWorld(new World());
	}
	
	public Tracer setWorld(World w)
	{
		this.world = w;
		return this;
	}
	
	public Tracer setCamera()
	{
		return this.setCamera(new Camera());
	}
	
	public Tracer setCamera(Camera c)
	{
		this.camera = c; 
		ppu = c.getPPU();
		width  = (int) ( ppu * c.getWidth()  );
		height = (int) ( ppu * c.getHeight() );
		return this;
	}
	
	public Tracer draw(int passes)
	{
		if (world == null)
		{
			this.setWorld();
		}
		if (camera == null)
		{
			this.setCamera();
		}
		
		double passDim = 1.0 / passes;
		
		image = new myImage(width, height);
		
		Color c = new Color();
		
		for (int y = 0; y < height; y++) 
		{
			
			for (int x = 0; x < width; x++)
			{
				c.setRGBA(0.0,0.0,0.0,0.0);
				
				for (int p = 0; p < passes; p++) {
					c = c.add(world.sample(camera.getRay(x,y)));
				}// END PIXEL SAMPLING
				
				c.dim(passDim);
				image.setPixel(x,y,c);
				
			}// END OF SCANLINE
			
			if ( y % 100 == 0 )
			{
				System.out.println("line " + y + " of " + height + "done");
			}
			// get time info
			// print line execution time
			
		}// END IMAGE RENDER
		
		return this;
		
	} // END DRAW METHOD
	
	public Tracer draw()
	{
		return this.draw(8);
	}
	
	public Tracer write()
	{
		String filename = "trace-" + width + "x" + height + ".png";
		return this.write(filename);
	}
	
	public Tracer write(String filename)
	{
		System.out.println("Printing to file '" + filename + "'");
		image.write(filename);
		System.out.println("All done!");
		return this;
	}
}

