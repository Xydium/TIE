package engine.physics;

import engine.math.Vector2f;
import engine.math.Vector2i;

/**
 * Axis-Alligned Bounding Box collider used for
 * testing and resolving collisions
 * 
 * @author Lenny Litvak
 *
 */
public class AABBCollider extends Collider
{
	private Vector2i size;
	
	/**
	 * Creates a new AABB collider with the given size
	 * 
	 * @param size the AABB's collider size
	 */
	public AABBCollider(Vector2i size)
	{
		this.size = size;
	}
	
	/**
	 * Checks whether this collider collides with the
	 * given collider
	 * 
	 * @param other the collider to test collisions with
	 * @return whether or not they collide
	 */
	public boolean collidesWith(Collider other)
	{
		if (!(other instanceof AABBCollider))
		{
			return false;
		}
		
		AABBCollider aabb2 = (AABBCollider)other;
		
		Vector2i pos1 = getTransform().getGlobalPosition();
		Vector2i pos2 = aabb2.getTransform().getGlobalPosition();
		
		return (pos1.getX() < pos2.getX() + aabb2.getSize().getX()
			 && pos1.getX() + size.getX() > pos2.getX()
			 && pos1.getY() < pos2.getY() + aabb2.getSize().getY()
			 && pos1.getY() + size.getY() > pos2.getY());
	}

	/**
	 * Gets the penetration vector of the collision between this
	 * collider and the given collider
	 * 
	 * @param other the collider to resolve collisions with
	 * @return the penetration vector
	 */
	public Vector2i resolveCollision(Collider other)
	{
		if (!(other instanceof AABBCollider))
		{
			return new Vector2i();
		}
		
		AABBCollider aabb2 = (AABBCollider)other;
		
		Vector2i p1 = getTransform().getGlobalPosition();
		Vector2i p2 = aabb2.getTransform().getGlobalPosition();
		
		Vector2i s1 = size;
		Vector2i s2 = aabb2.getSize();
		
		Vector2f centerVec = new Vector2f(p2.sub(p1));
		
		Vector2f[] collVectors = {new Vector2f((p2.getX() + s2.getX()) - (p1.getX() - s1.getX()), 0),
			new Vector2f(-((p1.getX() + s1.getX()) - (p2.getX() - s2.getX())), 0),
			new Vector2f(0, (p2.getY() + s2.getY()) - (p1.getY() - s1.getY())),
			new Vector2f(0, -((p1.getY() + s1.getY()) - (p2.getY() - s2.getY())))};
		
		float shortestLen = Float.MAX_VALUE;
		Vector2f shortest = new Vector2f();
		
		for (int i = 0; i < collVectors.length; i++)
		{
			if (centerVec.dot(collVectors[i]) < 0 && collVectors[i].getMagnitudeSq() < shortestLen)
			{
				shortestLen = collVectors[i].getMagnitudeSq();
				shortest = collVectors[i];
			}
		}
		
		return new Vector2i(shortest);
	}

	public void setSize(Vector2i size)
	{
		this.size = size;
	}
	
	public Vector2i getSize()
	{
		return size;
	}
}
