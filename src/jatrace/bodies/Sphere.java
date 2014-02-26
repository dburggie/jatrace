package jatrace.bodies;

import jatrace.*;

public class Sphere extends BasicBody
{
    protected double radius, RR;
    
    public Sphere()
    {
    	this.setDefaults();
    	this.setPosition(new Vect(0.0,0.0,0.0));
    	this.setColor(new Color(0.3,0.3,0.3));
    	this.setReflectivity(0.3);
    	this.setMatte(false);
    	this.setRadius(1.0);
    }
    
    public Sphere(Vect p, double r, Color c)
    {
    	this.setDefaults();
    	this.setPosition(p);
    	this.setColor(c);
    	this.setReflectivity(0.3);
    	this.setMatte(false);
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
        } else return hit;
    }
}
