package engine.math;

/**
 * a Transform which represents a position and a rotation
 * of an object, and can be translated or rotated
 * 
 * @author Lenny Litvak
 *
 */
public class Transform
{
	private Vector2 position;
	private float rotation;
	
	/**
	 * Creates a new Transform object
	 * sets the position to (0, 0) and
	 * sets the rotation to 0
	 */
	public Transform()
	{
		position = new Vector2();
		rotation = 0;
	}
	
	/**
	 * Creates a new Transform object
	 * sets the position to the given position and
	 * sets the rotation to 0
	 * 
	 * @param position the position of the transform
	 */
	public Transform(Vector2 position)
	{
		this.position = position;
		this.rotation = 0;
	}
	
	/**
	 * Creates a new Transform object
	 * sets the position to (0, 0) and
	 * sets the rotation to the given rotation
	 * 
	 * @param rotation the rotation of the transform
	 */
	public Transform(float rotation)
	{
		this.position = new Vector2();
		this.rotation = rotation;
	}
	
	/**
	 * Creates a new Transform object
	 * sets the position to the given position and
	 * sets the rotation to the given rotation
	 * 
	 * @param position the position of the transform
	 * @param rotation the rotation of the transform
	 */
	public Transform(Vector2 position, float rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	
	/**
	 * Translates the transform relative to its own rotation by the
	 * given translation
	 * 
	 * @param translation the vector to translate this transform by
	 * @return this Transform
	 */
	public Transform translateBy(Vector2 translation)
	{
		position = position.add(translation.rotateBy(rotation));
		
		return this;
	}
	
	/**
	 * Rotates this transform relative to its own rotation
	 * 
	 * @param rotation the rotation to rotate by
	 * @return this Transform
	 */
	public Transform rotateBy(float rotation)
	{
		this.rotation += rotation;
		
		return this;
	}
	
	/**
	 * Sets the position of this transform
	 * 
	 * @param x the x position of the transform
	 * @param y the y position of the transform
	 */
	public void setPosition(float x, float y)
	{
		position = new Vector2(x, y);
	}
	
	/**
	 * Sets the position of this transform
	 * 
	 * @param position the new position of the transform
	 */
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
	
	/**
	 * Sets the rotation of this transform
	 * 
	 * @param rotation the new rotation of the transform
	 */
	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}
	
	/**
	 * Gets the position of the transform
	 * 
	 * @return the position of the transform
	 */
	public Vector2 getPosition()
	{
		return position;
	}
	
	/**
	 * Gets the rotation of the transform
	 * 
	 * @return the rotation of the transform
	 */
	public float getRotation()
	{
		return rotation;
	}
}
