//package raytrace.threeD;

public class Color
{
    protected double red, green, blue, alpha;
    
    public Color()
    {
        red = 0.0; green = 0.0; blue = 0.0; alpha = 1.0;
    }
    
    public Color(double r, double g, double b)
    {
        red = r; green = g; blue = b; alpha = 1.0;
    }
    
    public Color(double r, double g, double b, double a)
    {
        red = r; green = g; blue = b; alpha = a;
    }
    
    public Color dup()
    {
        return new Color(red, green, blue, alpha);
    }
    
    public Color copy(Color c)
    {
        red = c.red; green = c.green; blue = c.blue;
        return this;
    }
    
    public Color add(Color c)
    {
        red += c.red; green += c.green; blue += c.blue; alpha += c.alpha;
        return this;
    }
    
    public int [] p()
    {
    	int [] i = new int [4];
        i[0] = Math.min(255, (int) (256 * red));
        i[1] = Math.min(255, (int) (256 * green));
        i[2] = Math.min(255, (int) (256 * blue));
        i[3] = Math.min(255, (int) (256 * alpha));
        return i;
    }
    
    public Color setRGB( double r, double g, double b )
    {
        red = r; green = g; blue = b;
        return this;
    }
    
    public Color setRGBA( double r, double g, double b, double a)
    {
        red = r; green = g; blue = b; alpha = a;
        return this;
    }
    
    public Color setalpha( double a )
    {
        alpha = a; return this;
    }
    
    public Color dim(double s)
    {
        red *= s; green *= s; blue *= s; alpha *= s; return this;
    }
    
    public Color gamma(double e)
    {
        red = Math.pow(red,e);
        green = Math.pow(green,e);
        blue = Math.pow(blue,e);
        return this;
    }
}
