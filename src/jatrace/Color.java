package jatrace;

/** This class provides many useful methods for dealing with colors while
 *  providing a high degree of color fidelity. */
public class Color
{
	protected double red, green, blue, alpha;
	
	
	/** Default constructor is true black. */
	public Color()
	{
		red = 0.0; green = 0.0; blue = 0.0; alpha = 1.0;
	}
	
	/** Initialize Color object to given rgb values. */
	public Color(double r, double g, double b)
	{
		red = r; green = g; blue = b; alpha = 1.0;
	}
	
	
	/** I really recommend against using an alpha channel. Alpha support is
	 *  unimplemented as yet with the rest of this package. */
	public Color(double r, double g, double b, double a)
	{
		red = r; green = g; blue = b; alpha = a;
	}
	
	/** Returns new Color instance that exactly copies the caller. */
	public Color dup()
	{
		return new Color(red, green, blue, alpha);
	}
	
	/** Copies the rgba values from the parameter into the caller. */
	public Color copy(Color c)
	{
		red = c.red; green = c.green; blue = c.blue; alpha = c.alpha;
		return this;
	}
	
	/** Adds the rgba values of the parameter to the caller. Be carefull with
	 *  this, values less than 0.0 or greater than 1.0 can cause issues. I
	 *  recommend calling the dim() method to account for this. */
	public Color add(Color c)
	{
		red += c.red; green += c.green; blue += c.blue; alpha += c.alpha;
		return this;
	}
	
	/** Returns an integer array so the rgba values will fit into bytes. Note:
	 *  currently the alpha channel is unsupported as it was causing many
	 *  rendering issues. */
	public int [] p()
	{
		int [] i = new int [4];
		i[0] = Math.min(255, (int) (256 * red));
		i[1] = Math.min(255, (int) (256 * green));
		i[2] = Math.min(255, (int) (256 * blue));
		i[3] = 255;
		//i[3] = Math.min(255, (int) (256 * alpha));
		return i;
	}
	
	/** Sets rgb values to desired parameters. Note that values below 0.0 and
	 *  above 1.0, though technically safe, could be a source of bugs. These
	 *  values would correspond to over and under saturated colors. */
	public Color setRGB( double r, double g, double b )
	{
		red = r; green = g; blue = b;
		return this;
	}
	
	/** See notice for setRGB method. */
	public Color setRGBA( double r, double g, double b, double a)
	{
		red = r; green = g; blue = b; alpha = a;
		return this;
	}
	
	/** See notice for setRGB method. */
	public Color setalpha( double a )
	{
		alpha = a; return this;
	}
	
	/** Dims the color by the specified scalar. Negative values and values
	 *  greater than 1.0 will probably cause Weird Things (tm), but are
	 *  supported for edge cases. Be cautious. */
	public Color dim(double s)
	{
		red *= s; green *= s; blue *= s; alpha *= s; return this;
	}
	
	/** If given sample is subminimally or supermaximally saturated, returns
	 *  the true minimum or maximum sample values. */
	public static double makeSafe(double sample)
	{
		if (sample > 1.0) { return 1.0; }
		if (sample < 0.0) { return 0.0 }
		return sample;
	}
	
	/** Raises sample values to the specified exponent. Useful in brightening
	 *  (values less than one) and darkening (values greater than one) images
	 *  in a fairly realistic manner. Negative values have an unknown - if 
	 *  any - utility. Note: calling this function will reset sample values
	 *  outside of 0.0 to 1.0 to true black or true white respectively. */
	public Color gamma(double e)
	{
		red = Math.pow( makeSafe(red), e );
		green = Math.pow( makeSafe(green), e );
		blue= Math.pow( makeSafe(blue),e );
		return this;
	}
}
