package engine.math;

/**
 * A 2-Dimensional Vector with an x value and a y value
 * 
 * @author Lenny Litvak
 *
 */
public class Vector2
{
	private float x, y;
	
	/**
	 * Creates a new Vector2 object with its x and y
	 * parameters set to 0
	 */
	public Vector2()
	{
		x = 0;
		y = 0;
	}
	
	/**
	 * Creates a new Vector2 object with its x and y
	 * parameters set to the given values
	 * 
	 * @param x the x value of the vector
	 * @param y the y value of the vector
	 */
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calculates the dot product of this vector and
	 * the given vector
	 * 
	 * @param v2 the vector to dot product with
	 * @return the dot product of the two vectors
	 */
	public float dot(Vector2 v2)
	{
		return x * v2.x + y * v2.y;
	}
	
	/**
	 * Gets the magnitude (length) of the vector
	 * 
	 * @return the magnitude of the vector
	 */
	public float getMagnitude()
	{
		return (float)Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Gets the unit (normalized) vector from the
	 * current vector
	 * 
	 * @return the normalized vector
	 */
	public Vector2 getUnit()
	{
		float mag = getMagnitude();
		float invMag = mag == 0 ? 0 : 1.0f / mag;
		
		return new Vector2(x * invMag, y * invMag);
	}
	
	/**
	 * Rotates the vector by the given angle,
	 * and returns a new rotated vector
	 * 
	 * @param angle the angle to rotate by
	 * @return a new rotated vector
	 */
	public Vector2 rotateBy(float angle)
	{
		double sinA = Math.sin(angle);
		double cosA = Math.cos(angle);
		
		return new Vector2((float)(x * cosA - y * sinA), (float)(x * sinA + y * cosA));
	}
	
	/**
	 * Adds the given vector to this vector and
	 * creates a new sum vector to be returned
	 * 
	 * @param v2 The vector to add to this vector
	 * @return The sum vector
	 */
	public Vector2 add(Vector2 v2)
	{
		return new Vector2(x + v2.x, y + v2.y);
	}
	
	/**
	 * Subtracts the given vector from this vector and
	 * creates a new difference vector to be returned
	 * 
	 * @param v2 The vector to subtract from this vector
	 * @return The difference vector
	 */
	public Vector2 sub(Vector2 v2)
	{
		return new Vector2(x - v2.x, y - v2.y);
	}
	
	/**
	 * Multiplies the given vector by this vector and
	 * creates a new product vector to be returned
	 * 
	 * @param v2 The vector to multiply by this vector
	 * @return The product vector
	 */
	public Vector2 mul(Vector2 v2)
	{
		return new Vector2(x * v2.x, y * v2.y);
	}
	
	/**
	 * Divides this vector by the given vector and
	 * creates a new quotient vector to be returned
	 * 
	 * @param v2 The vector to divide this vector by
	 * @return The quotient vector
	 */
	public Vector2 div(Vector2 v2)
	{
		return new Vector2(x / v2.x, y / v2.y);
	}
	
	/**
	 * Adds the given number to this vector and
	 * creates a new sum vector to be returned
	 * 
	 * @param n The number to be added
	 * @return The sum vector
	 */
	public Vector2 add(float n)
	{
		return new Vector2(x + n, y + n);
	}
	
	/**
	 * Subtracts the given number to this vector and
	 * creates a new difference vector to be returned
	 * 
	 * @param n The number to be subtracted
	 * @return The difference vector
	 */
	public Vector2 sub(float n)
	{
		return new Vector2(x - n, y - n);
	}
	
	/**
	 * Multiplies the given number by this vector and
	 * creates a new product vector to be returned
	 * 
	 * @param n The number to be multiplied by
	 * @return The product vector
	 */
	public Vector2 mul(float n)
	{
		return new Vector2(x * n, y * n);
	}
	
	/**
	 * Divides this vector by the given number and
	 * creates a new quotient vector to be returned
	 * 
	 * @param n The number to be divided by
	 * @return The quotient vector
	 */
	public Vector2 div(float n)
	{
		return new Vector2(x / n, y / n);
	}
	
	/**
	 * Sets the x value of the vector
	 * 
	 * @param x the x value of the vector
	 */
	public void setX(float x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the y value of the vector
	 * 
	 * @param y the y value of the vector
	 */
	public void setY(float y)
	{
		this.y = y;
	}
	
	/**
	 * Gets the x value of the vector
	 * 
	 * @return the x value of the vector
	 */
	public float getX()
	{
		return x;
	}
	
	/**
	 * Gets the y value of the vector
	 * 
	 * @return the y value of the vector
	 */
	public float getY()
	{
		return y;
	}
	
	/**
	 * Gets a value from the vector based on a given
	 * index
	 * 0: x
	 * 1: y
	 * 
	 * @param i the index of the vector component
	 * @return the corresponding vector component
	 */
	public float get(int i)
	{
		switch (i)
		{
			case 0:
				return x;
			case 1:
				return y;
			default:
				throw new IndexOutOfBoundsException();
		}
	}
}
