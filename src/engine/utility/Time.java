package engine.utility;

public final class Time
{
	private static final double SECOND = 1000000000L;
	
	public static double getTime()
	{
		return (double)System.nanoTime() / (double)SECOND;
	}
}
