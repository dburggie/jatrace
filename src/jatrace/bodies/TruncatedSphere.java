package jatrace.bodies;

import jatrace.*;

/** A sphere that has been truncated and is thus more-or-less barrel-shaped. */
public class TruncatedSphere extends Sphere
{
	protected Plane plusCap, minusCap;
	protected double cosine = 1.0;
	private Vector capUp, capDown;
	private boolean initLocked = true;
	
	public TruncatedSphere(Vector p, double r, Color c)
	{
		super(p,r,c);
		
		initLocked = false;
		
		setOrientation(new Vector(0.0,1.0,0.0));
		setCosine(0.7);
	}
	
	/** Gets vector normal to Body at given point. */
	public Vector getNormal(Vector point)
	{
		Vector secant = point.sub(position);
		
		//are we on the surface of the sphere?
		if (Math.abs(secant.dot(secant) - RR) < 0.000001)
		{
			return secant.norm();
		}
		
		//we're on one of the end caps then
		else
		{
			
			//return orientation if on the top cap
			if (secant.dot(orientation) > 0.0)
			{
				return orientation.dup();
			}
			
			//return minus orientation if we're on the bottom
			else
			{
				return orientation.minus();
			}
		}
		
	}
	
	
	public double intersection(Ray ray)
	{
		
		Vector S;
		double SD, SS;
		
		S = ray.o().sub(position);
		
		//get distance to sphere center
		SS = S.dot(S);
		
		//just don't bother if it's too far away
		if (SS > 1000000.0) return -1.0;
		
		//measure angle between ray direction and to sphere center
		SD = S.dot(ray.d());
		
		//intersections are at the solutions to a quadratic equation
		
		//get the terms under the radical
		double radical = SD * SD + RR - SS;
		
		//if radical is negative, we have no real solutions.
		if (radical < 0.0) { return -1.0; }
		//otherwise, we get the root of it
		else radical = Math.sqrt(radical);
		
		//get both sphere hits
		double [] sphereHits = new double[] { -1 * SD - radical, -1 * SD + radical };
		//if the farther one is negative, we miss totally
		if (sphereHits[1] < 0.0) { return -1.0; }
		
		//get the intersections with the planes
		double [] planeHits;
		double hit1 = plusCap.intersection(ray);
		double hit2 = minusCap.intersection(ray);
		
		//order the planeHits
		if (hit1 < hit2) { planeHits = new double[] { hit1, hit2 }; }
		else { planeHits = new double[] {hit2, hit1}; }
		
		//miss if both sphere hits are before the plane hits (or vice versa)
		if (planeHits[1] < sphereHits[0] || sphereHits[1] < planeHits[0])
		{
			return -1.0;
		}
		
		
		/* The lowest hit is not ever correct, but neither is the last one.
		 * Thus, after discounting the lowest hit, our proper intersection is
		 * the first positive of the other type.
		 */
		if (planeHits[0] < sphereHits[0])
		{
			if (sphereHits[0] > 0.0)
			{
				return sphereHits[0];
			}
			
			else if (sphereHits[1] > 0.0)
			{
				return sphereHits[1];
			}
		}
		
		else
		{
			if (planeHits[0] > 0.0)
			{
				return planeHits[0];
			}
			else if (planeHits[1] > 0.0)
			{
				return planeHits[1];
			}
		}
		
		//If we get here, we're on a very weird edge case and it's safe to miss
		return -1.0;
		
		/*
    def intersection(self, ray):
        """Returns distance from ray to closest intersection with sphere."""
        
        S = ray.o - self.center
        SD = S.dot( ray.d )
        SS = S.dot(S)
        
        # no hit if sphere is really far away
        if SS > bounds.too_far ** 2:
            return -1.0
        
        radical = SD ** 2 + self.R - SS
        # negative radical implies no solutions
        if radical < 0.0:
            return -1.0
        
        radical **= 0.5
        hits = [-1 * SD - radical,  -1 * SD + radical]
        if hits[0] < bounds.too_close:
            if hits[1] < bounds.too_small:
                return -1.0
        
        pp = self._pp.intersection(ray)
        pn = self._pn.intersection(ray)
        
        if pp < pn:
            hitp = [pp,pn]
        else:
            hitp = [pn,pp]
        
        # if two plane hits before sphere hits, we miss
        if hitp[1] < hits[0]:
            return -1.0
        
        # if two sphere hits before plane hits, we miss
        if hits[1] < hitp[0]:
            return -1.0
        
        # if the second thing hit is forward, that's our distance
        hit = max(hits[0],hitp[0])
        if hit > 0:
            return hit
        # otherwise it's the third hit (if positive)
        hit = min(hits[1],hitp[1])
        if hit > 0:
            return hit
        # otherwise, we didn't hit anything
        else:
            return -1.0
        */
		
		
		
		
	}
	
	@Override
	public void setOrientation(Vector o)
	{
		super.setOrientation(o);
		
		if (!initLocked) { resetCaps(); }
		
	}
	
	/** This sets the "cosine" of the body, which is a measure of the angle
	 *  between the orientation vector and the rim of the planar caps
	 *  (specifically, it is the cosine of that angle). */
	public void setCosine(double cos)
	{
		cosine = cos;
		resetCaps();
	}
	
	private void resetCaps()
	{
		capUp = position.dup();
		capUp.trans( orientation.dup().scale(cosine * radius) );
		
		capDown = position.dup();
		capDown.trans( orientation.dup().scale(-1.0 * cosine * radius) );
		
		if (plusCap == null)
		{
			plusCap = new Plane();
			plusCap.setColor(color);
		}
		
		if (minusCap == null)
		{
			minusCap = new Plane();
			minusCap.setColor(color);
		}
		
		plusCap.setPosition(capUp);
		plusCap.setNormal(orientation);
		
		minusCap.setPosition(capDown);
		minusCap.setNormal(orientation.minus());
	}
	
}
