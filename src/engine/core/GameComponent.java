package engine.core;

import engine.math.Transform;

/**
 * A component of a GameObject
 * 
 * @author Lenny Litvak
 *
 */
public abstract class GameComponent
{
	private GameObject parent;
	private String tag = "GameComponent";
	
	/**
	 * Called immediately after the game has processed input
	 */
	public void input()
	{
	}
	
	/**
	 * Called every update frame of the game
	 */
	public void update()
	{
	}
	
	/**
	 * Called every time the game renders
	 */
	public void render()
	{
	}
	
	/**
	 * Sets the parent of the component
	 * 
	 * @param parent the parent of the component
	 */
	public void setParent(GameObject parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Gets the parent of the component
	 * 
	 * @return component's parent
	 */
	public GameObject getParent()
	{
		return parent;
	}
	
	/**
	 * Gets the application this component is a part of
	 * 
	 * @return the component's application
	 */
	public Application getApplication()
	{
		return parent.getApplication();
	}
	
	/**
	 * Gets the transform of the component's parent object
	 * 
	 * @return parent object's transform
	 */
	public Transform getTransform()
	{
		return parent.getTransform();
	}
	
	/**
	 * Gets the tag of the game component
	 * 
	 * @return the tag of the game component
	 */
	public String getTag() 
	{
		return tag;
	}
	
	/**
	 * Sets the tag of the game component
	 * 
	 * @param tag the new tag of the component
	 */
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
}
