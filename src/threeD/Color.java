package threeD;

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
        return new Color(red, green, blue);
    }
    
    public Color copy(Color c)
    {
        red = c.red; green = c.green; blue = c.blue;
        return this;
    }
    
    public Color add(Color c)
    {
        red += c.red; green += c.green; blue += c.blue;
        return this;
    }
    
    private byte dtob(double d)
    {
        assert (d <= 1.0 && d >= 0.0);
        return (byte) Math.min(127, d * 256 - 128);
    }
    
    public byte [] p()
    {
        byte p[] = new byte [3];
        p[0] = dtob(red);
        p[1] = dtob(green);
        p[2] = dtob(blue);
        return p;
    }
    
    public byte [] p(boolean alpha_channel)
    {
        byte p[];
        if (alpha_channel) 
        {
            p = new byte [4];
            p[0] = dtob(red);
            p[1] = dtob(green);
            p[2] = dtob(blue);
            p[3] = dtob(alpha);
            return p;
        }
        else return this.p();
    }
    
    public Color setrgb( double r, double g, double b )
    {
        red = r; green = g; blue = b;
        return this;
    }
    
    public Color setalpha( double a )
    {
        alpha = a; return this;
    }
    
    public Color dim(double s)
    {
        red *= s; green *= s; blue *= s; return this;
    }
    
    public Color gamma(double e)
    {
        red = Math.pow(red,e);
        green = Math.pow(green,e);
        blue = Math.pow(blue,e);
        return this;
    }
}
