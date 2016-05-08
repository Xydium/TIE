package engine.rendering;

/**
 * RGB color value
 * 
 * @author Lenny Litvak
 *
 */
public class Color
{
	private float r, g, b;
	
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
		this.r = r;
		this.g = g;
		this.b = b;
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
}
