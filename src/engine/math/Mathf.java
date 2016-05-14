package engine.math;

/**
 * Float based math functions for ease of use and 
 * cleaner implementation.
 * 
 * @author Tim Hornick
 *
 */
public class Mathf {

	public static float degToRads(float degrees) 
	{
		return (float) Math.toRadians(degrees);
	}
	
	public static float radsToDeg(float radians) 
	{
		return (float) Math.toDegrees(radians);
	}
	
	public static float abs(float n)
	{
		return Math.abs(n);
	}
	
	public static float sin(float a)
	{
		return (float)Math.sin(a);
	}
	
	public static float cos(float a)
	{
		return (float)Math.cos(a);
	}
	
	public static float tan(float a)
	{
		return (float)Math.tan(a);
	}
}
