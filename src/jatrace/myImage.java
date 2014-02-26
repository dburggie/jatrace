package jatrace;

import jatrace.threeD.Color;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class myImage
{
	protected final static int ct = BufferedImage.TYPE_4BYTE_ABGR;
	
	protected BufferedImage bi;
	protected WritableRaster wr;
	protected int width, height;
	
	public myImage(int w, int h)
	{
		width = w; height = h;
		bi = new BufferedImage(w,h,ct);
		wr = bi.getRaster();
	}
	
	public myImage setPixel(int x, int y, Color c)
	{
		wr.setPixel(x,y,c.p());
		return this;
	}
	
	public myImage write(String filename)
	{
		try {
			File of = new File(filename);
			ImageIO.write(bi, "png", of);
		} catch (IOException e) {
			String err = "failed to open or write to file ";
			err += filename;
			System.out.println(err);
			System.exit(1);
		}
		return this;
	}
}
