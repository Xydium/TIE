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
	
}
