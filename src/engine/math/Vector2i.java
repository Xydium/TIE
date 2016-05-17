package engine.math;

/**
 * A 2-Dimensional Vector with an x value and a y value
 * with values stored as integers
 * 
 * @author Lenny Litvak
 *
 */
public class Vector2i
{
	private int x, y;
	
	/**
	 * Creates a new Vector2 object with its x and y
	 * parameters set to 0
	 */
	public Vector2i()
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
	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new Vector2 object with its x and y
	 * parameters set to the given values cast as integers
	 * 
	 * @param x the x value of the vector
	 * @param y the y value of the vector
	 */
	public Vector2i(float x, float y)
	{
		this.x = (int)x;
		this.y = (int)y;
	}
	
	/**
	 * Creates a new Vector2 object with its x and y
	 * parameters set to the given values cast as integers
	 * 
	 * @param x the x value of the vector
	 * @param y the y value of the vector
	 */
	public Vector2i(double x, double y)
	{
		this.x = (int)x;
		this.y = (int)y;
	}
	
	/**
	 * Converts a Vector2f to a new Vector2i object
	 * 
	 * @param v2f the vector to convert
	 */
	public Vector2i(Vector2f v2f)
	{
		x = (int)v2f.getX();
		y = (int)v2f.getY();
	}
	
	/**
	 * Rotates the vector by the given angle,
	 * and returns a new rotated vector
	 * 
	 * @param angle the angle to rotate by
	 * @return a new rotated vector
	 */
	public Vector2i rotateBy(float angle)
	{
		double sinA = Math.sin(angle);
		double cosA = Math.cos(angle);
		
		return new Vector2i(x * cosA - y * sinA, x * sinA + y * cosA);
	}
	
	/**
	 * Adds the given vector to this vector and
	 * creates a new sum vector to be returned
	 * 
	 * @param v2 The vector to add to this vector
	 * @return The sum vector
	 */
	public Vector2i add(Vector2i v2)
	{
		return new Vector2i(x + v2.x, y + v2.y);
	}
	
	/**
	 * Subtracts the given vector from this vector and
	 * creates a new difference vector to be returned
	 * 
	 * @param v2 The vector to subtract from this vector
	 * @return The difference vector
	 */
	public Vector2i sub(Vector2i v2)
	{
		return new Vector2i(x - v2.x, y - v2.y);
	}
	
	/**
	 * Multiplies the given vector by this vector and
	 * creates a new product vector to be returned
	 * 
	 * @param v2 The vector to multiply by this vector
	 * @return The product vector
	 */
	public Vector2i mul(Vector2i v2)
	{
		return new Vector2i(x * v2.x, y * v2.y);
	}
	
	/**
	 * Divides this vector by the given vector and
	 * creates a new quotient vector to be returned
	 * 
	 * @param v2 The vector to divide this vector by
	 * @return The quotient vector
	 */
	public Vector2i div(Vector2i v2)
	{
		return new Vector2i(x / v2.x, y / v2.y);
	}
	
	/**
	 * Adds the given number to this vector and
	 * creates a new sum vector to be returned
	 * 
	 * @param n The number to be added
	 * @return The sum vector
	 */
	public Vector2i add(int n)
	{
		return new Vector2i(x + n, y + n);
	}
	
	/**
	 * Subtracts the given number to this vector and
	 * creates a new difference vector to be returned
	 * 
	 * @param n The number to be subtracted
	 * @return The difference vector
	 */
	public Vector2i sub(int n)
	{
		return new Vector2i(x - n, y - n);
	}
	
	/**
	 * Multiplies the given number by this vector and
	 * creates a new product vector to be returned
	 * 
	 * @param n The number to be multiplied by
	 * @return The product vector
	 */
	public Vector2i mul(int n)
	{
		return new Vector2i(x * n, y * n);
	}
	
	/**
	 * Divides this vector by the given number and
	 * creates a new quotient vector to be returned
	 * 
	 * @param n The number to be divided by
	 * @return The quotient vector
	 */
	public Vector2i div(int n)
	{
		return new Vector2i(x / n, y / n);
	}
	
	/**
	 * Sets the x value of the vector
	 * 
	 * @param x the x value of the vector
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the y value of the vector
	 * 
	 * @param y the y value of the vector
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Gets the x value of the vector
	 * 
	 * @return the x value of the vector
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gets the y value of the vector
	 * 
	 * @return the y value of the vector
	 */
	public int getY()
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
	public int get(int i)
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
		return String.format("(%s, %s)", x, y);
	}
}
