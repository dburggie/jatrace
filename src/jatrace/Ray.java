package jatrace;

public class Ray
{
    protected Vector origin, direction;
    
    public Ray()
    {
        origin = new Vector(0.0,0.0,0.0);
        direction = new Vector(0.0,1.0,0.0);
    }
    
    public Ray(Vector O, Vector D)
    {
        this.setOrigin(O);
        this.setDirection(D);
    }
    
    public Ray dup()
    {
        return new Ray(origin, direction);
    }
    
    public Ray setOrigin(Vector v)
    {
        origin = v.dup();
        return this;
    }
    
    public Ray setDirection(Vector v)
    {
        direction = v.dup().norm();
        return this;
    }
    
    public Vector o() { return origin.dup(); }
    public Vector d() { return direction.dup(); }
    
    public Vector follow(double distance)
    {
        return origin.dup().trans(direction, distance);
    }
    
    public Ray reflect(Vector position, Vector normal)
    {
        origin = position.dup();
        double dot = direction.dot(normal);
        direction.trans(normal, -2.0 * dot);
        return this;
    }
    
    public Ray refract(Vector position, Vector normal, double T_sine)
    {
        // check for total internal reflection
        if (T_sine > 1.0) return this.reflect(position, normal);
        
        // we know where our new origin is
        this.setOrigin(position);
        
        // new direction is between normal and our original direction
        Vector p = direction.dup().proj(normal).trans(direction, -1.0).scale(1 - T_sine);
        
        // on the off chance we're near zero, don't change the direction
        if (p.len() < 0.0000001) return this;
        
        else return this.setDirection(p);
    }
}
