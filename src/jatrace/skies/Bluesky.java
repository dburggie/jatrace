package jatrace.skies;

import jatrace.*;

public class Bluesky implements Sky
{
    protected Vector sun;
    protected final Color color = new Color(0.3,0.3,0.9999);
    
    //constructors
    public Bluesky()
    {
        sun = new Vector(0.0,1.0,0.0);
    }
    
    public Bluesky(Vector direction)
    {
        sun = direction.dup();
    }
    
    @Override
    public Color getColor(Vector direction)
    {
        double exp = Math.pow(Math.max(0.0,direction.dot(sun)), 20.0);
        return color.dup().gamma(1 - exp);
    }
    
    @Override
    public Vector [] getLight()
    {
        Vector L[] = new Vector [1];
        L[0] = sun.dup().delta(0.05).norm();
        return L;
    }
}
