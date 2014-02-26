package jatrace;

final class linkedBody
{
	private static linkedBody head = null;

	private linkedBody nextLink;
	private Body body;
	private boolean inserted;
	
	public static linkedBody top()
	{
		return head;
	}
	
	public linkedBody(Body b)
	{
		body = b;
		inserted = false;
		this.insert();
	}
	
	public Body b()
	{
		return body;
	}
	
	public linkedBody next()
	{
		return nextLink;
	}
	
	private void insert()
	{
		if (!inserted)
		{
			nextLink = head;
			head = this;
			inserted = true;
		}
	}


}
