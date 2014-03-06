package jatrace;

/** This class provides a means to keep track of a collection of objects with
 *  the Body interface through a linked list. Each link will have a unique ID
 *  integer that can be used to find and or remove it from the collection. */
public class LinkedBody
{
	private static LinkedBody firstLink = null;
	private static int maxID = 0;

	private LinkedBody nextLink;
	private Body body;
	private boolean inserted;
	private int myID;
	
	
	/** Fetches the first object in the linked list. */
	public static LinkedBody top()
	{
		return firstLink;
	}
	
	/** Fetches the object with the specified id number. Returns null if there
	 *  is no object of that id in the list. */
	public static LinkedBody getByID(int id)
	{
		if (id < 0 || id > maxID) { return null; }
		
		LinkedBody topBody = top();
		LinkedBody body = topBody;
		
		while (body == null)
		{
			
			if (body.myID == id)
			{
				return body;
			}
			
			body = body.next();
			if (body == topBody) { return null;}
			
		}
		
		return null;
	}
	
	/** Removes and fetches the object specified by id. Returns null if no
	 *  object of that id is in the list. */
	public static LinkedBody removeByID(int id)
	{
		LinkedBody link = firstLink;
		
		// check for bounding and edge cases
		if (link == null || id < 0 || id > maxID) return null;
		if (link.myID == id)
		{
			link.inserted = false;
			firstLink = null;
			return link;
			
		}
		
		if (link.nextLink == null) { return null; }
		
		// find body with the id we want
		while (link.nextLink.myID != id)
		{
			
			link = link.nextLink;
			if (link.nextLink == null) { return null; }
			
		} 
		
		link.nextLink.inserted = false;
		link.nextLink = link.nextLink.nextLink;
		return link.nextLink;
	}
	
	/** Instantiates a new link containing the specified Body interface and
	 *  inserts it into the list. */
	public LinkedBody(Body b)
	{
		body = b;
		inserted = false;
		this.insert();
	}
	
	/** Fetches a reference to the Body object this link wraps. */
	public Body b()
	{
		return body;
	}
	
	/** Fetches the next link down from caller. A null reference will be
	 *  returned if the caller is the tail object. */
	public LinkedBody next()
	{
		return nextLink;
	}
	
	/** Inserts a new LinkedBody object into the list only if it is not already
	 *  in the list. */
	public void insert()
	{
		if (!inserted)
		{
			maxID += 1;
			myID = maxID;
			nextLink = firstLink;
			firstLink = this;
			inserted = true;
		}
	}


}
