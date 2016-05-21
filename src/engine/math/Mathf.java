package engine.math;

/**
 * Float based math functions for ease of use and 
 * cleaner implementation.
 * 
 * @author Tim Hornick
 * @author Lenny Litvak
 *
 */
public class Mathf
{
	/**
	 * @see java.lang.Math#toRadians(double)
	 */
	public static float toRadians(float degrees) 
	{
		return (float) Math.toRadians(degrees);
	}
	
	/**
	 * @see java.lang.Math#toDegrees(double)
	 */
	public static float toDegrees(float radians) 
	{
		return (float) Math.toDegrees(radians);
	}
	
	/**
	 * @see java.lang.Math#sqrt(double)
	 */
	public static float sqrt(float n)
	{
		return (float) Math.sqrt(n);
	}
	
	/**
	 * @see java.lang.Math#abs(double)
	 */
	public static float abs(float n)
	{
		return Math.abs(n);
	}
	
	/**
	 * @see java.lang.Math#sin(double)
	 */
	public static float sin(float a)
	{
		return (float) Math.sin(a);
	}
	
	/**
	 * @see java.lang.Math#cos(double)
	 */
	public static float cos(float a)
	{
		return (float) Math.cos(a);
	}
	
	/**
	 * @see java.lang.Math#tan(double)
	 */
	public static float tan(float a)
	{
		return (float) Math.tan(a);
	}
	
	/**
	 * @see java.lang.Math#atan2(double, double)
	 */
	public static float atan2(float y, float x)
	{
		return (float) Math.atan2(y, x);
	}
	
	/**
	 * Linearly interpolates between points a and b,
	 * with the percentage c
	 * 
	 * @param a the initial value to interpolate from
	 * @param b the final value to interpolate to
	 * @param c the percentage to interpolate
	 * @return the interpolated value
	 */
	public static float lerp(float a, float b, float c)
	{
		return a + (b - a) * c;
	}
}
