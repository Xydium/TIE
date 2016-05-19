package engine.physics;

import engine.core.GameComponent;

/**
 * Class for collider components which will check and
 * resolve their collisions with each other
 * 
 * @author Lenny Litvak
 *
 */
public abstract class Collider extends GameComponent
{
	/**
	 * Resolves all collisions of all objects within the collider list
	 */
	/*public static void resolveCollisions()
	{
		for (int i = 0; i < colliders.size(); i++)
		{
			for (int j = 1; j < colliders.size(); j++)
			{
				if (colliders.get(i).collidesWith(colliders.get(j)))
				{
					Vector2f penetration = colliders.get(i).resolveCollision(colliders.get(j));
					colliders.get(i).getTransform().setGlobalPosition(colliders.get(i).getTransform().getGlobalPosition().add(penetration));
				}
			}
		}
	}*/
	
	/**
	 * Checks whether this collider collides with the
	 * given collider
	 * 
	 * @param other the collider to test collisions with
	 * @return whether or not they collide
	 */
	public abstract boolean collidesWith(Collider other);
	
	/**
	 * Resolves the collision between this collider and the other
	 * collider
	 * 
	 * @param other the collider to resolve collisions with
	 */
	public abstract void resolveCollision(Collider other);
}
