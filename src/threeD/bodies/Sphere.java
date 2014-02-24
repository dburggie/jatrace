package threeD.bodies;

import threeD.Ray;
import threeD.Color;
import threeD.Vect;

public class Sphere implements body
{
    protected Vect center;
    protected Color color;
    protected double radius, RR, reflectivity, opacity, index;

    @Override
    public Color getColor(Vect point)
    {
        return color.dup();
    }
    
    @Override
    public Vect getNormal(Vect point)
    {
        return point.sub(center).norm();
    }
    
    @Override
    public double getReflectivity(Vect point)
    {
        return reflectivity;
    }
    
    @Override
    public double getOpacity()
    {
        return opacity;
    }
    
    @Override
    public double getIndex()
    {
        return index;
    }
    
    @Override
    public double intersection(Ray ray)
    {
        Vect S;
        double SD, SS;
        
        S = ray.o().sub(center);
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
