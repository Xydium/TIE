package engine.math;

/**
 * A Transform which represents a position and a rotation
 * of an object, and can be translated or rotated
 * 
 * @author Lenny Litvak
 *
 */
public class Transform
{
	private Transform parent;
	private Vector2i position;
	private float rotation;
	
	/**
	 * Creates a new Transform object
	 * sets the position to (0, 0) and
	 * sets the rotation to 0
	 */
	public Transform()
	{
		this(new Vector2i(), 0);
	}
	
	/**
	 * Creates a new Transform object
	 * sets the position to the given position and
	 * sets the rotation to 0
	 * 
	 * @param position the position of the transform
	 */
	public Transform(Vector2i position)
	{
		this(position, 0);
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
		this(new Vector2i(), rotation);
	}
	
	/**
	 * Creates a new Transform object
	 * sets the position to the given position and
	 * sets the rotation to the given rotation
	 * 
	 * @param position the position of the transform
	 * @param rotation the rotation of the transform
	 */
	public Transform(Vector2i position, float rotation)
	{
		parent = null;
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
	public Transform translateBy(Vector2i translation)
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
	
	public float distanceTo(int x, int y)
	{
		Vector2i gPos = getGlobalPosition();
		
		int dx = gPos.getX() - x;
		int dy = gPos.getY() - y;
		
		return Mathf.sqrt(dx * dx + dy * dy);
	}
	
	public float distanceTo(Vector2i point)
	{
		return distanceTo(point.getX(), point.getY());
	}
	
	public float distanceTo(Transform other)
	{
		return distanceTo(other.getGlobalPosition());
	}
	
	public float angleTo(int x, int y)
	{
		Vector2i gPos = getGlobalPosition();
		
		return Mathf.atan2(x - gPos.getX(), y - gPos.getY());
	}
	
	public float angleTo(Vector2i point)
	{
		return angleTo(point.getX(), point.getY());
	}
	
	public void lookAt(int x, int y)
	{
		setGlobalRotation(angleTo(x, y));
	}
	
	public void lookAt(Vector2i point)
	{
		lookAt(point.getX(), point.getY());
	}
	
	/**
	 * Sets the local position of this transform
	 * 
	 * @param x the x position of the transform
	 * @param y the y position of the transform
	 */
	public void setPosition(float x, float y)
	{
		position = new Vector2i(x, y);
	}
	
	/**
	 * Sets the local position of this transform
	 * 
	 * @param position the new position of the transform
	 */
	public void setPosition(Vector2i position)
	{
		this.position = position;
	}
	
	/**
	 * Sets the global position of the transform
	 * 
	 * @param position the global position of the transform
	 */
	public void setGlobalPosition(Vector2i position)
	{
		if (parent == null)
		{
			this.position = position;
			return;
		}
		
		this.position = position.sub(parent.getGlobalPosition()).rotateBy(-parent.getRotation());
	}
	
	/**
	 * Sets the global position of the transform
	 * 
	 * @param x the x component of the global position of the transform
	 * @param y the y component of the global position of the transform
	 */
	public void setGlobalPosition(int x, int y)
	{
		setGlobalPosition(new Vector2i(x, y));
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
	
	public void setGlobalRotation(float rotation)
	{
		if (parent == null)
		{
			this.rotation = rotation;
			return;
		}
		
		this.rotation = -parent.getGlobalRotation() - rotation;
	}

	/**
	 * Sets the parent of the transformation
	 * 
	 * @param parent the parent of the transformation
	 */
	public void setParent(Transform parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Gets the local position of the transform
	 * 
	 * @return the position of the transform
	 */
	public Vector2i getPosition()
	{
		return position;
	}
	
	/**
	 * Gets the global position of the transform
	 * 
	 * @return the global position of the transform
	 */
	public Vector2i getGlobalPosition()
	{
		if (parent == null)
		{
			return position;
		}
		
		return parent.getGlobalPosition().add(position.rotateBy(parent.getRotation()));
	}
	
	/**
	 * Gets the local rotation of the transform
	 * 
	 * @return the rotation of the transform
	 */
	public float getRotation()
	{
		return rotation;
	}
	
	/**
	 * Gets the global rotation of the transform
	 * 
	 * @return the global rotation of the transform
	 */
	public float getGlobalRotation()
	{
		if (parent == null)
		{
			return rotation;
		}
		
		return parent.getGlobalRotation() + rotation;
	}
	
	/**
	 * Gets the parent of the transformation
	 * 
	 * @return the parent of the transformation
	 */
	public Transform getParent()
	{
		return parent;
	}
}
