package engine.math;

/**
 * A 2-Dimensional Vector with an x value and a y value
 * with values stored as floats
 * 
 * @author Lenny Litvak
 *
 */
public class Vector2f
{
	private float x, y;
	
	/**
	 * Creates a new Vector2 object with its x and y
	 * parameters set to 0
	 */
	public Vector2f()
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
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Converts a Vector2i to a new Vector2f object
	 * 
	 * @param v2i the vector to convert
	 */
	public Vector2f(Vector2i v2i)
	{
		x = v2i.getX();
		y = v2i.getY();
	}
	
	/**
	 * Calculates the dot product of this vector and
	 * the given vector
	 * 
	 * @param v2 the vector to dot product with
	 * @return the dot product of the two vectors
	 */
	public float dot(Vector2f v2)
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
	 * Gets the magnitude^2 of the vector
	 * 
	 * @return the magnitude^2 of the vector
	 */
	public float getMagnitudeSq()
	{
		return x * x + y * y;
	}
	
	/**
	 * Gets the unit (normalized) vector from the
	 * current vector
	 * 
	 * @return the normalized vector
	 */
	public Vector2f getUnit()
	{
		float mag = getMagnitude();
		float invMag = mag == 0 ? 0 : 1.0f / mag;
		
		return new Vector2f(x * invMag, y * invMag);
	}
	
	/**
	 * Rotates the vector by the given angle,
	 * and returns a new rotated vector
	 * 
	 * @param angle the angle to rotate by
	 * @return a new rotated vector
	 */
	public Vector2f rotateBy(float angle)
	{
		double sinA = Math.sin(angle);
		double cosA = Math.cos(angle);
		
		return new Vector2f((float)(x * cosA - y * sinA), (float)(x * sinA + y * cosA));
	}
	
	/**
	 * Gets the angle of the vector
	 * 
	 * @return the angle of the vector
	 */
	public float getAngle()
	{
		Vector2f norm = getUnit();
		
		return Mathf.atan2(norm.getY(), norm.getX());
	}
	
	/**
	 * Gets the angle to a given point
	 * 
	 * @param point the point to get the angle to
	 * @return the angle to the given point
	 */
	public float getAngleTo(Vector2f point)
	{
		return Mathf.atan2(x - point.getX(), y - point.getY());
	}
	
	/**
	 * Adds the given vector to this vector and
	 * creates a new sum vector to be returned
	 * 
	 * @param v2 The vector to add to this vector
	 * @return The sum vector
	 */
	public Vector2f add(Vector2f v2)
	{
		return new Vector2f(x + v2.x, y + v2.y);
	}
	
	/**
	 * Subtracts the given vector from this vector and
	 * creates a new difference vector to be returned
	 * 
	 * @param v2 The vector to subtract from this vector
	 * @return The difference vector
	 */
	public Vector2f sub(Vector2f v2)
	{
		return new Vector2f(x - v2.x, y - v2.y);
	}
	
	/**
	 * Multiplies the given vector by this vector and
	 * creates a new product vector to be returned
	 * 
	 * @param v2 The vector to multiply by this vector
	 * @return The product vector
	 */
	public Vector2f mul(Vector2f v2)
	{
		return new Vector2f(x * v2.x, y * v2.y);
	}
	
	/**
	 * Divides this vector by the given vector and
	 * creates a new quotient vector to be returned
	 * 
	 * @param v2 The vector to divide this vector by
	 * @return The quotient vector
	 */
	public Vector2f div(Vector2f v2)
	{
		return new Vector2f(x / v2.x, y / v2.y);
	}
	
	/**
	 * Adds the given number to this vector and
	 * creates a new sum vector to be returned
	 * 
	 * @param n The number to be added
	 * @return The sum vector
	 */
	public Vector2f add(float n)
	{
		return new Vector2f(x + n, y + n);
	}
	
	/**
	 * Subtracts the given number to this vector and
	 * creates a new difference vector to be returned
	 * 
	 * @param n The number to be subtracted
	 * @return The difference vector
	 */
	public Vector2f sub(float n)
	{
		return new Vector2f(x - n, y - n);
	}
	
	/**
	 * Multiplies the given number by this vector and
	 * creates a new product vector to be returned
	 * 
	 * @param n The number to be multiplied by
	 * @return The product vector
	 */
	public Vector2f mul(float n)
	{
		return new Vector2f(x * n, y * n);
	}
	
	/**
	 * Divides this vector by the given number and
	 * creates a new quotient vector to be returned
	 * 
	 * @param n The number to be divided by
	 * @return The quotient vector
	 */
	public Vector2f div(float n)
	{
		return new Vector2f(x / n, y / n);
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
	
	public String toString()
	{
		return String.format("(%.2f, %.2f)", x, y);
	}
}
