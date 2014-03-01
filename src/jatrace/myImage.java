package jatrace;

import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

/** Provides a simplified interface to the java.awt.image, java.io, and
 *  javax.imageio libraries. */
public class myImage
{
	protected final static int ct = BufferedImage.TYPE_4BYTE_ABGR;
	
	protected BufferedImage bi;
	protected WritableRaster wr;
	protected int width, height;
	
	/** Instantiates a new image with a buffer w pixels wide and h pixels tall. */
	public myImage(int w, int h)
	{
		width = w; height = h;
		bi = new BufferedImage(w,h,ct);
		wr = bi.getRaster();
	}
	
	/** Sets pixel (x,y) to specified color. These coordinates follow the
	 *  standard of (0,0) being the top-left of the image. */
	public myImage setPixel(int x, int y, Color c)
	{
		wr.setPixel(x,y,c.p());
		return this;
	}
	
	/** Writes image buffer to given filename. Note: calls System.exit(1) on
	 *  IOException, thus may exit program uncleanly. */
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
