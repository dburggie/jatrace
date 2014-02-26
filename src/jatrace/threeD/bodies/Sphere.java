package jatrace.threeD.bodies;

import jatrace.threeD.Ray;
import jatrace.threeD.Color;
import jatrace.threeD.Vect;

public class Sphere extends Body
{
    protected double radius, RR;
    
    public Sphere(Vect p, double r, Color c)
    {
    	this.setDefaults();
    	this.setPosition(p);
    	this.setColor(c);
    	this.setMatte(true);
    	this.setRadius(r);
    }
    
    public Sphere setRadius(double r)
    {
    	radius = r; RR = r * r;
    	return this;
    }

    @Override
    public Vect getNormal(Vect point)
    {
        return point.sub(position).norm();
    }
    
    @Override
    public double intersection(Ray ray)
    {
        Vect S;
        double SD, SS;
        
        S = ray.o().sub(position);
        SD = S.dot(ray.d());
        SS = S.dot(S);
        
        if (SS > 1000000.0) return -1.0;
        
        double radical = SD * SD + RR - SS;
        if (radical < 0.0) return -1.0;
        else radical = Math.sqrt(radical);
        
        double hit = -1 * SD - radical;
        
        if (hit < 0.0001)
        {
            hit = -1 * SD + radical;
            if (hit < 0.0001) 
                return -1.0;
            else return hit;
        }
        else return hit;
    }
}
