package threeD.skies;

import threeD.Vect;
import threeD.Color;

public class Bluesky implements Sky
{
    protected Vect sun;
    protected final Color color = new Color(0.3,0.3,0.9999);
    
    //constructors
    public Bluesky()
    {
        sun = new Vect(0.0,1.0,0.0);
    }
    
    public Bluesky(Vect direction)
    {
        sun = direction.dup();
    }
    
    @Override
    public Color getColor(Vect direction)
    {
        double exp = Math.pow(direction.dot(sun), 20.0);
        return color.dup().gamma(1 - exp);
    }
    
    @Override
    public Vect [] getLight()
    {
        Vect L[] = new Vect [1];
        L[0] = sun.dup().delta(0.05).norm();
        return L;
    }
}
