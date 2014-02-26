package jatrace.threeD;

class defaults
{
	public static final int ppu = 100;
	public static final double width = 1.0;
	public static final double height = 1.0;
	public static final Vect position = new Vect(0.0,1.0,10.0);
	public static final Vect focus = new Vect(0.0,0.0,0.0);
	public static final Vect orientation = new Vect(0.0,1.0,0.0);
}

public class Camera
{
	protected Vect position, focus, forward, up, right, corner, xstep, ystep;
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
	
	public Camera(Vect origin, Vect focus, Vect u, double w, double h, int p)
	{
		initLock = true;
		this.setPosition(origin);
		this.setFocus(focus);
		this.setOrientation(u);
		this.setWindow(w,h);
		this.setPPU(p);
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
	
	public Camera setOrientation(Vect Up)
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
		corner = focus.dup().trans(up, w / 2.0).trans(right, h / -2.0);
		return this;
	}
	
	public Camera setFocus(Vect f)
	{
		forward = f.sub(position).norm();
		focus = f.dup();
		if (!initLock) return this.reset();
		else return this;
	}
	
	public Camera setPosition(Vect p)
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
		Vect or, di;
		double dx = x + Math.random();
		double dy = y + Math.random();
		
		or = position.dup();
		if (delta) or.delta(d);
		
		di = corner.dup().trans(xstep,dx).trans(ystep,dy).sub(or);
		
		return new Ray(or,di);
	}
}
