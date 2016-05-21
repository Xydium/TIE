package engine.rendering;

/**
 * RGBA color value
 * 
 * @author Lenny Litvak
 *
 */
public class Color
{
	private float r, g, b, a;
	
	/**
	 * Creates a new color object with the given
	 * RGB values
	 * 
	 * @param r the r value of the color
	 * @param g the g value of the color
	 * @param b the b value of the color
	 */
	public Color(float r, float g, float b)
	{
		this(r, g, b, 1.f);
	}
	
	/**
	 * Creates a new color object with the given
	 * RGBA values
	 * 
	 * @param r the r value of the color
	 * @param g the g value of the color
	 * @param b the b value of the color
	 * @param a the a value of the color
	 */
	public Color(float r, float g, float b, float a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	/**
	 * Sets the r value of the color
	 * 
	 * @param r the r value of the color
	 */
	public void setR(float r)
	{
		this.r = r;
	}
	
	/**
	 * Sets the g value of the color
	 * 
	 * @param g the g value of the color
	 */
	public void setG(float g)
	{
		this.g = g;
	}
	
	/**
	 * Sets the b value of the color
	 * 
	 * @param b the b value of the color
	 */
	public void setB(float b)
	{
		this.b = b;
	}
	
	/**
	 * Gets the r value of the color
	 * 
	 * @return the r value of the color
	 */
	public float getR()
	{
		return r;
	}
	
	/**
	 * Gets the g value of the color
	 * 
	 * @return the g value of the color
	 */
	public float getG()
	{
		return g;
	}
	
	/**
	 * Gets the b value of the color
	 * 
	 * @return the b value of the color
	 */
	public float getB()
	{
		return b;
	}
	
	/**
	 * Gets the a value of the color
	 * 
	 * @return the a value of the color
	 */
	public float getA()
	{
		return a;
	}
	
	/**
	 * Creates a new java.awt.Color object with the
	 * same color as this object
	 * 
	 * @return the java.awt.Color conversion of this color
	 */
	public java.awt.Color toAWTColor()
	{
		return new java.awt.Color(r, g, b, a);
	}
}
