package jatrace.bodies;

import jatrace.*;

public class TexturedSphere extends Sphere
{
	
	private final static double pi = Math.PI;
	
	private boolean textureLoaded = false;
	private myImage texture;
	private double halfWidth;
	private int fullHeight;
	
	private Vector north, west, east;
	
	
	
	/** Default sphere with no texture. Will not clip with rays until a valid
	 *  texture file has been loaded. */
	public TexturedSphere()
	{
		super();
		setCoordinates(new Vector(0.0,1.0,0.0), new Vector(1.0,0.0,0.0));
		setMatte(true);
		setSpecularity(100.0);
		textureLoaded = false;
	}
	
	/** Initializes new sphere with given position and radius, loading the
	 *  texture at the given path. */
	public TexturedSphere(Vector position, double radius, String texturePath)
	{
		
		this(); setPosition(position); setRadius(radius);
		
		load(texturePath);
		
	}
	
	
	/** Loads image at given path as the sphere's texture. */
	public void load(String texturePath)
	{
		texture = myImage.read(texturePath);
		
		if (texture != null)
		{
			halfWidth = texture.getWidth() / 2.0;
			fullHeight = texture.getHeight();
			textureLoaded = true;
		}
		
		else
		{
			System.out.println("Failed to load texture '" + texturePath + "'.");
			halfWidth = 0.0;
			fullHeight = 0;
			textureLoaded = false;
		}
	}
	
	
	/** Sets the orientation of the textured sphere.
	 *  @param pole the sphere's north pole
	 *  @param meridian a vector on the sphere's prime meridian */
	public void setCoordinates(Vector pole, Vector meridian)
	{
		
		east = pole.cross(meridian);
		if (east.len() < 0.000001)
		{
			north.setxyz(0.0,1.0,0.0);
			west.setxyz(0.0,0.0,1.0);
			east = north.cross(west);
		}
		
		else
		{
			east.norm();
			west = east.cross(pole).norm();
			north = west.cross(east).norm();
		}
		
	}
	
	@Override
	public Color getColor(Vector point)
	{
		Vector v = point.dup().scale(1/radius);
		
		double south = Math.acos(north.dot(v)) / pi;
		double eastwest = Math.acos(west.dot(v)) / pi;
		boolean isEast = (east.dot(v) > 0.0) ? true : false;
		
		//x and y are coordinates on the texture
		int x, y;
		
		//check if point is east or west of meridian
		if ( east.dot(v) > 0.0 )
		{
			x = (int) ( halfWidth + halfWidth * eastwest );
		}
		else
		{
			x = (int) ( halfWidth - halfWidth * eastwest );
		}
		
		y = (int) ( fullHeight * south );
		
		return texture.getPixel(x,y);
		
	}
	
	@Override
	public double intersection(Ray ray)
	{
		if ( textureLoaded )
		{
			return super.intersection(ray);
		}
		
		else
		{
			return -1.0;
		}
	}
}
