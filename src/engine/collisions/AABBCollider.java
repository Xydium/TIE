package engine.collisions;

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
	 * Resolves the collision between this collider and the other
	 * collider
	 * 
	 * @param other the collider to resolve collisions with
	 */
	public void resolveCollision(Collider other)
	{
		if (!(other instanceof AABBCollider))
		{
			return;
		}
		
		AABBCollider aabb2 = (AABBCollider)other;
		
		Vector2i p1 = getTransform().getGlobalPosition();
		Vector2i p2 = aabb2.getTransform().getGlobalPosition();
		
		Vector2i s1 = size;
		Vector2i s2 = aabb2.getSize();
		
		Vector2f c1 = new Vector2f(p1.add(s1.div(2)));
		Vector2f c2 = new Vector2f(p2.add(s2.div(2)));
		
		Vector2f centerVec = c2.sub(c1);
		
		Vector2f[] penDirs = {
			new Vector2f(0, p2.getY() - s1.getY() - p1.getY()), new Vector2f(0, p2.getY() + s2.getY() - p1.getY()),
			new Vector2f(p2.getX() - s1.getX() - p1.getX(), 0), new Vector2f(p2.getX() + s2.getX() - p1.getX(), 0)
		};
		
		float shortestLen = Float.MAX_VALUE;
		Vector2f shortest = new Vector2f();
		
		for (int i = 0; i < penDirs.length; i++)
		{
			if (centerVec.dot(penDirs[i]) < 0 && penDirs[i].getMagnitudeSq() < shortestLen)
			{
				shortestLen = penDirs[i].getMagnitudeSq();
				shortest = penDirs[i];
			}
		}
		
		getTransform().setGlobalPosition(p1.add(new Vector2i(shortest)));
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
