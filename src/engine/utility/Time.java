package engine.utility;

/**
 * Utility wrapper class for time utilities
 * 
 * @author Lenny Litvak
 *
 */
public final class Time
{
	private static final double SECOND = 1000000000L;
	
	/**
	 * Gets the time since the Unix epoch in seconds
	 * 
	 * @return the time since the Unix epoch
	 */
	public static double getTime()
	{
		return (double)System.nanoTime() / (double)SECOND;
	}
}
