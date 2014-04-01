package jatrace.bodies;

import jatrace.*;

public class Ellipse extends Plane
{
	
	private Vector xAxis = new Vector(1.0,0.0,0.0); 
	private Vector yAxis = new Vector(0.0,1.0,0.0);
	public final void setAxes(Vector xDir, Vector yDir)
	{
		
		xLength = xDir.len();
		xAxis = xDir.dup().norm();
		yLength = yDir.len();
		yAxis = yDir.dup().norm();
		setNormal(xAxis.cross(yAxis));
		axesSet = true;
	}
	
	private boolean axesSet = false;
	private double xLength, yLength;
	public final void setAxisLengths(double x, double y)
	{
		xLength = x; yLength = y;
	}
	
	@Override
	public double intersection(Ray ray)
	{
		if ( ! axesSet )
		{
			return -1.0;
		}
		
		double distance = super.intersection(ray);
		
		if (distance < 0.0) return distance;
		
		Vector p = ray.follow(distance);
		p.trans(position, -1.0);
		
		double xd = p.dot(xAxis);
		if (xd > xLength) return -1.0;
		
		double yd = p.dot(yAxis);
		if (yd > yLength) return -1.0;
		
		return distance;
		
	}
}
