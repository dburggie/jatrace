package raytrace;

import raytrace.threeD.Color;
//import javax.Graphics; //etc

public class Image
{
	protected int width, height;
	protected Color image[][];
	
	public Image(int w, int h)
	{
		width = w; height = h;
		image = new Color[h][w];
		for (int y = 0; y < h; y++)
		{
			for (int x = 0; x < w; x++)
			{
				image[y][x] = new Color();
			}
		}
	}
	
	public Image setPixel(int x, int y, Color color)
	{
		image[y][x] = color;
		return this;
	}
	
	public Color getPixel(int x, int y)
	{
		return image[y][x].dup();
	}
	
	public Image gamma(double g)
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				image[y][x].gamma(g);
			}
		}
		return this;
	}
	
	public Image toPNG(String filename)
	{
		//do a bunch of arcane shit.
		return this;
	}
}
