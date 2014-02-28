package jatrace;

class defaults
{
	public static final int ppu = 100;
	public static final double width = 1.0;
	public static final double height = 1.0;
	public static final Vector position = new Vector(0.0,1.0,10.0);
	public static final Vector focus = new Vector(0.0,0.0,0.0);
	public static final Vector orientation = new Vector(0.0,1.0,0.0);
}

public class Camera
{
	protected Vector position, focus, forward, up, right, corner, xstep, ystep;
	protected int ppu;
	protected double width, height, d;
	protected boolean delta, initLock;
	
	public Camera()
	{
		initLock = true;
		this.setPosition(defaults.position);
		this.setFocus(defaults.focus);
		this.setOrientation(defaults.orientation);
		this.setWindow(defaults.width, defaults.height);
		this.setPPU(defaults.ppu);
		delta = false;
		initLock = false;
	}
	
	public Camera(Vector origin, Vector focus, double w, double h)
	{
		initLock = true;
		this.setPosition(origin);
		this.setFocus(focus);
		this.setOrientation(defaults.orientation);
		this.setWindow(w,h);
		this.setPPU(defaults.ppu);
		delta = false;
		initLock = false;
	}
	
	public Camera(Vector p, Vector f, Vector o, double w, double h, int P)
	{
		initLock = true;
		this.setPosition(p);
		this.setFocus(f);
		this.setOrientation(o);
		this.setWindow(w,h);
		this.setPPU(P);
		delta = false;
		initLock = false;
	}
	
	public Camera dup()
	{
		Camera c = new Camera(position,focus,up,width,height,ppu);
		if (delta) c.setDelta(d);
		return c;
	}
	
	protected Camera reset()
	{
		this.setWindow(width,height);
		this.setPPU(ppu);
		return this;
	}
	
	public Camera resetDelta()
	{
		delta = false;
		return this;
	}
	
	public Camera setDelta(double D)
	{
		delta = true;
		d = D;
		return this;
	}
	
	public Camera setOrientation(Vector Up)
	{
		right = forward.cross(Up).norm();
		up = right.cross(forward).norm();
		return this;
	}
	
	public Camera setPPU(int p)
	{
		ppu = p;
		xstep = right.dup().scale(1.0 / p);
		ystep = up.dup().scale(-1.0 / p);
		return this;
	}
	
	public int getPPU() { return ppu; }
	public double getWidth() { return width; }
	public double getHeight() {return height; }
	
	public Camera setWindow(double w, double h)
	{
		width = w; height = h;
//		corner = focus.dup().trans(up, w / 2.0).trans(right, h / -2.0);
		corner = focus.dup().trans(up, h / 2.0).trans(right, w / -2.0);
		return this;
	}
	
	public Camera setFocus(Vector f)
	{
		forward = f.sub(position).norm();
		focus = f.dup();
		if (!initLock) return this.reset();
		else return this;
	}
	
	public Camera setPosition(Vector p)
	{
		position = p.dup();
		if (!initLock)
		{
			this.setFocus(focus);
			this.reset();
		}
		return this;
	}
	
	public Ray getRay(int x, int y)
	{
		Vector or, di;
		double dx = x + Math.random();
		double dy = y + Math.random();
		
		or = position.dup();
		if (delta) or.delta(d);
		
		di = corner.dup().trans(xstep,dx).trans(ystep,dy).sub(or);
		
		return new Ray(or,di);
	}
}
