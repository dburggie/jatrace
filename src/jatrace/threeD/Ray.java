package raytrace.threeD;

public class Ray
{
    protected Vect origin, direction;
    
    public Ray()
    {
        origin = new Vect(0.0,0.0,0.0);
        direction = new Vect(0.0,1.0,0.0);
    }
    
    public Ray(Vect O, Vect D)
    {
        this.setOrigin(O);
        this.setDirection(D);
    }
    
    public Ray dup()
    {
        return new Ray(origin, direction);
    }
    
    public Ray setOrigin(Vect v)
    {
        origin = v.dup();
        return this;
    }
    
    public Ray setDirection(Vect v)
    {
        direction = v.dup().norm();
        return this;
    }
    
    public Vect o() { return origin.dup(); }
    public Vect d() { return direction.dup(); }
    
    public Vect follow(double distance)
    {
        return origin.dup().trans(direction, distance);
    }
    
    public Ray reflect(Vect position, Vect normal)
    {
        origin = position.dup();
        double dot = direction.dot(normal);
        direction.trans(normal, -2.0 * dot);
        return this;
    }
    
    public Ray refract(Vect position, Vect normal, double T_sine)
    {
        // check for total internal reflection
        if (T_sine > 1.0) return this.reflect(position, normal);
        
        // we know where our new origin is
        this.setOrigin(position);
        
        // new direction is between normal and our original direction
        Vect p = direction.dup().proj(normal).trans(direction, -1.0).scale(1 - T_sine);
        
        // on the off chance we're near zero, don't change the direction
        if (p.len() < 0.0000001) return this;
        
        else return this.setDirection(p);
    }
}
