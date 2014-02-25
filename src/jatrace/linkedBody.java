package jatrace;

import jatrace.threeD.bodies.Body;

private class linkedBody
{
	private static linkedBody head = null;

	private linkedBody nextLink;
	private Body body;
	private boolean inserted;
	
	public final static linkedBody top()
	{
		return head;
	}
	
	public final linkedBody(Body b)
	{
		body = b;
		inserted = false;
		this.insert();
	}
	
	public final Body b()
	{
		return body;
	}
	
	public final linkedBody next()
	{
		return nextLink;
	}
	
	private final void insert()
	{
		if (!inserted)
		{
			nextLink = head;
			head = this;
			inserted = true;
		}
	}


}
