package engine.physics;

import engine.math.Vector2;
import engine.rendering.Rectangle;

/**
 * Axis-Alligned Bounding Box collider used for
 * testing and resolving collisions
 * 
 * @author Lenny Litvak
 *
 */
public class AABBCollider extends Collider
{
	private Rectangle rect;
	
	/**
	 * Creates a new AABB collider with the given
	 * rectangle as a bounding box
	 * 
	 * @param rect the rect for the bounding box
	 */
	public AABBCollider(Rectangle rect)
	{
		this.rect = rect;
		Collider.addCollider(this);
	}
	
	/**
	 * Creates a new AABB collider with the given size
	 * acting as the size for the bounding box's rectangle
	 * 
	 * @param size the AABB's collider size
	 */
	public AABBCollider(Vector2 size)
	{
		this(new Rectangle(size));
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
		
		Vector2 cPos1 = getTransform().getGlobalPosition().sub(rect.getSize());
		Vector2 cPos2 = aabb2.getTransform().getGlobalPosition().sub(aabb2.getRect().getSize());
		
		Vector2 sz1 = rect.getAdjustedSize().mul(2);
		Vector2 sz2 = aabb2.getRect().getAdjustedSize().mul(2);
		
		return (cPos1.getX() < cPos2.getX() + sz2.getX()
			 && cPos1.getX() + sz1.getX() > cPos2.getX()
			 && cPos1.getY() < cPos2.getY() + sz2.getY()
			 && cPos1.getY() + sz1.getY() > cPos2.getY());
	}

	/**
	 * Gets the penetration vector of the collision between this
	 * collider and the given collider
	 * 
	 * @param other the collider to resolve collisions with
	 * @return the penetration vector
	 */
	public Vector2 resolveCollision(Collider other)
	{
		if (!(other instanceof AABBCollider))
		{
			return new Vector2();
		}
		
		AABBCollider aabb2 = (AABBCollider)other;
		
		Vector2 p1 = getTransform().getGlobalPosition();
		Vector2 p2 = aabb2.getTransform().getGlobalPosition();
		
		Vector2 s1 = rect.getAdjustedSize();
		Vector2 s2 = aabb2.getRect().getAdjustedSize();
		
		Vector2 centerVec = p2.sub(p1);
		
		Vector2[] collVectors = {new Vector2((p2.getX() + s2.getX()) - (p1.getX() - s1.getX()), 0),
			new Vector2(-((p1.getX() + s1.getX()) - (p2.getX() - s2.getX())), 0),
			new Vector2(0, (p2.getY() + s2.getY()) - (p1.getY() - s1.getY())),
			new Vector2(0, -((p1.getY() + s1.getY()) - (p2.getY() - s2.getY())))};
		
		float shortestLen = Float.MAX_VALUE;
		Vector2 shortest = new Vector2();
		
		for (int i = 0; i < collVectors.length; i++)
		{
			if (centerVec.dot(collVectors[i]) < 0 && collVectors[i].getMagnitudeSq() < shortestLen)
			{
				shortestLen = collVectors[i].getMagnitudeSq();
				shortest = collVectors[i];
			}
		}
		
		return shortest;
	}

	/**
	 * Gets the rectangle that is being used to test collisions
	 * 
	 * @return the AABB's rect
	 */
	public Rectangle getRect()
	{
		return rect;
	}
}
