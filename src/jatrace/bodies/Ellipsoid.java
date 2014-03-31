package jatrace.bodies;

import jatrace.*;

public class Ellipsoid extends Body
{
	
	private Vector xAxis, yAxis, zAxis;
	private double xLength, yLength, zLength;
	
	
	/** Transforms vector to new vector space such that this spheroid is a 
	 *  unit ball aligned to the space's primary axes.
	 *  @param v vector to be transformed */
	private Vector reAxis(Vector v)
	{
		
		return v.setxyz(
				xAxis.dot(v) / xLength,
				yAxis.dot(v) / yLength,
				zAxis.dot(v) / zLength
			);
		
	}
	
	
	/** Initializes Ellipsoid to given position and axes. */
	public Ellipsoid(Vector position, Vector up, Vector meridian)
	{
		
		super();
		setOrientation(up,meridian);
		setAxisLengths(1.0,1.0,1.0);
		
	}
	
	/** Sets x, y, and z axes according to parameters given.
	 *  @param up the vector that gives the spheroid's internal "up"
	 *  @param meridian a vector in the direction of the spheroids meridian */
	public void setOrientation(Vector up, Vector meridian)
	{
		yAxis = up.dup().norm();
		zAxis = meridian.cross(up).norm();
		xAxis = yAxis.cross(zAxis);
	}
	
	/** Sets the lengths of the Ellipsoid's internal x, y, and z axes to those
	 *  given. */
	public void setAxisLengths(double x, double y, double z)
	{
		xLength = x; yLength = y; zLength = z;
	}
	
	@Override
	public Vector getNormal(Vector point)
	{
		return point.sub(position).norm();
	}
	
	@Override
	public double intersection(Ray ray)
	{
		
		//Plan of attack:
		//  1. transform ray into vector space with spheroid a unit sphere at 0
		//  2. find intersection with this unit sphere
		
		Vector rayOrigin = reAxis( ray.o().trans(position, -1.0) ),
				rayDirection = reAxis(ray.d());
		
		double DS = rayDirection.dot(rayOrigin),
				DD = rayDirection.dot(rayDirection),
				SS = rayOrigin.dot(rayOrigin);
		
		//intersections are at solutions to quadratic equation
		
		//get term under radical
		double radical = DS + DD - DD * SS;
		
		//no solutions if negative
		if (radical < 0.0)
		{
			return -1.0;
		}
		
		else
		{
			radical = Math.sqrt(radical) / DD;
		}
		
		//calculate first distance
		double distance = -DS / DD - radical;
		
		//check this intersection is positive
		if (distance > 0.0)
		{
			return distance;
		}
		
		//else calculate second intersection
		else
		{
			distance = -DS / DD + radical;
			
			//last chance for positive solution
			if (distance > 0.0)
			{
				return distance;
			}
			
			else
			{
				return -1.0;
			}
		}
		
	}
	
}
