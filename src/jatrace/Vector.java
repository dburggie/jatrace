package jatrace;

public class Vector
{
    protected double x, y, z;
    
    public Vector()
    {
        x = 0.0; y = 0.0; z = 0.0;
    }
    
    public Vector( double X, double Y, double Z )
    {
        x = X; y = Y; z = Z;
    }
    
    public Vector dup()
    {
        return new Vector(x,y,z);
    }
    
    public Vector copy(Vector v)
    {
        x = v.x; y = v.y; z = v.z;
        return this;
    }
    
    public Vector setxyz(double X, double Y, double Z)
    {
        x = X; y = Y; z = Z;
        return this;
    }
    
    public Vector trans( Vector v )
    {
        x += v.x; y += v.y; z += v.z;
        return this;
    }
    
    public Vector trans( Vector v, double s )
    {
        x += s * v.x;
        y += s * v.y;
        z += s * v.z;
        return this;
    }
    
    public Vector trans( double dx, double dy, double dz )
    {
        x += dx; y += dy; z += dz;
        return this;
    }
    
    public double dot(Vector v)
    {
        return x * v.x + y * v.y + z * v.z;
    }
    
    public Vector cross(Vector v)
    {
        return new Vector(
                y * v.z - z * v.y, 
                z * v.x - x * v.z,
                x * v.y - y * v.x 
                );
    }
    
    public Vector scale(double s)
    {
        x *= s; y *= s; z *= s;
        return this;
    }
    
    public Vector sub(Vector v)
    {
        return new Vector(x - v.x, y - v.y, z - v.z);
    }
    
    public double len()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }
    
    public Vector proj(Vector v)
    {
        double dot = this.dot(v);
        return this.copy(v).scale(dot);
    }
    
    public Vector norm()
    {
        double l = this.len();
        if (l < 0.0000001)
        {
            System.out.println("Tried to normalize a zero Vectoror.");
            return this.setxyz(0.0,1.0,0.0);
        }
        x /= l; y /= l; z /= l;
        return this;
    }
    
    public Vector delta(double d)
    {
        double dx,dy,dz;
        do {
            dx = 2.0 * Math.random() - 1.0;
            dy = 2.0 * Math.random() - 1.0;
            dz = 2.0 * Math.random() - 1.0;
        } while (dx * dx + dy * dy + dz * dz < 1.0);
        
        return this.trans(dx * d, dy * d, dz * d);
    }
    
	public String toString()
	{
		String s = "x: ";
		s += x;
		s += " y: ";
		s += y;
		s += " z: ";
		s += z;
		return s;
	}
}
