package engine.resmgmt;

/**
 * Stores the number of times the resource has been referenced
 * 
 * @author Lenny Litvak
 *
 */
public class Resource
{
	private int referenceCount;
	
	/**
	 * Creates a new Resource object and
	 * initializes its reference count to 1
	 */
	public Resource()
	{
		referenceCount = 1;
	}
	
	/**
	 * Increments up the reference count for the resource
	 */
	public void addReference()
	{
		referenceCount++;
	}
	
	/**
	 * Removes one from the reference count of the resource
	 * and returns whether or not there are no remaining references
	 * 
	 * @return whether the number of references == 0
	 */
	public boolean removeReference()
	{
		referenceCount--;
		
		return referenceCount == 0;
	}
}
