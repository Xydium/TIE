package engine.core;

import java.util.ArrayList;

import engine.math.Transform;

/**
 * An object which resides within a game, with a
 * transformation as well as a hierarchy of parents and
 * children
 * 
 * @author Lenny Litvak
 *
 */
public class GameObject
{
	private ArrayList<GameComponent> components;
	private ArrayList<GameObject> children;
	private GameObject parent;
	
	private ArrayList<GameObject> removeList;
	
	private Transform transform;
	
	private Application application;
	
	private String tag;
	
	/**
	 * Creates a new GameObject object
	 */
	public GameObject()
	{
		components = new ArrayList<GameComponent>();
		children = new ArrayList<GameObject>();
		parent = null;
		transform = new Transform();
		
		removeList = new ArrayList<GameObject>();
		
		application = null;
		tag = "GameObject";
	}
	
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
	 * Inputs all of the children and components of the GameObject
	 */
	public void inputAll()
	{
		for (GameObject obj : children)
		{
			obj.input();
			obj.inputAll();
		}
		
		for (GameComponent comp : components)
		{
			comp.input();
		}
	}
	
	/**
	 * Updates all of the children and components of the GameObject
	 */
	public void updateAll()
	{
		for (GameObject obj : children)
		{
			obj.update();
			obj.updateAll();
		}
		
		for (GameComponent comp : components)
		{
			comp.update();
		}
	}
	
	/**
	 * Renders all of the children and components of the GameObject
	 */
	public void renderAll()
	{
		for (GameObject obj : children)
		{
			obj.render();
			obj.renderAll();
		}
		
		for (GameComponent comp : components)
		{
			comp.render();
		}
	}
	
	/**
	 * Actually removes all of the removed objects in the removed list
	 */
	public void cleanRemovedObjects()
	{
		for (GameObject obj : removeList)
		{
			obj.parent = null;
			obj.transform.setParent(null);
			children.remove(obj);
		}
		
		removeList.clear();
	}
	
	/**
	 * Adds a child to the game object's children
	 * 
	 * @param child the child to add to the object
	 */
	public void addChild(GameObject child)
	{
		child.setApplication(application);
		child.parent = this;
		child.transform.setParent(transform);
		children.add(child);
	}
	
	/**
	 * Adds all of the listed game objects as children
	 * 
	 * @param children the children to add to the object
	 */
	public void addAllChildren(GameObject... children)
	{
		for (GameObject obj : children)
		{
			addChild(obj);
		}
	}
	
	public void cleanRemoved()
	{
		for (GameObject obj : removeList)
		{
			removeChild(obj);
		}
	}
	
	/**
	 * Adds the given component to the game object
	 * 
	 * @param component the component to add
	 */
	public void addComponent(GameComponent component)
	{
		components.add(component);
		component.setParent(this);
	}
	
	/**
	 * Adds all of the given components to the game object
	 * 
	 * @param components the components to add
	 */
	public void addAllComponents(GameComponent... components)
	{
		for (GameComponent comp : components)
		{
			addComponent(comp);
		}
	}
	
	/**
	 * Removes the given child from the object's children
	 * 
	 * Warning: attempts to remove it immediately; not suggested
	 * 
	 * @param child the child to remove
	 * @return the child removed
	 */
	public GameObject removeChild(GameObject child)
	{
		child.parent = null;
		child.transform.setParent(null);
		children.remove(child);
		
		return child;
	}
	
	/**
	 * Removes the child at the given index from
	 * the object's children
	 * 
	 * Warning: attempts to remove it immediately; not suggested
	 * 
	 * @param i the index of the child to remove
	 * @return the child removed
	 */
	public GameObject removeChild(int i)
	{
		GameObject child = children.remove(i);
		child.parent = null;
		child.transform.setParent(null);
		
		return child;
	}
	
	public void removeChildSafely(GameObject child)
	{
		removeList.add(child);
	}
	
	/**
	 * Gets a list of the components of this GameObject
	 * 
	 * @return a list of this object's components
	 */
	public ArrayList<GameComponent> getComponents()
	{
		return components;
	}
	
	/**
	 * Gets a list of the children of this GameObject
	 * 
	 * @return a list of this object's children
	 */
	public ArrayList<GameObject> getChildren()
	{
		return children;
	}
	
	/**
	 * Gets the parent object of this GameObject
	 * 
	 * @return the parent object of this GameObject
	 */
	public GameObject getParent()
	{
		return parent;
	}
	
	/**
	 * Gets the transform of the GameObject
	 * 
	 * @return the transform of the object
	 */
	public Transform getTransform()
	{
		return transform;
	}
	
	/**
	 * Sets the application which contains this GameObject
	 * 
	 * @param application the application which will contain this GameObject
	 */
	public void setApplication(Application application)
	{
		this.application = application;
	}
	
	/**
	 * Gets the application which contains this GameObject
	 * 
	 * @return the application of this GameObject
	 */
	public Application getApplication()
	{
		return application;
	}
	
	/**
	 * Gets the tag of the game object
	 * 
	 * @return the tag of the game object
	 */
	public String getTag()
	{
		return tag;
	}
	
	/**
	 * Sets the tag of the game object
	 * 
	 * @param tag the new tag of the object
	 */
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	/**
	 * Gets the first component to match the given tag
	 * 
	 * @param tag the component's tag
	 * @return the first component with the given tag
	 */
	public GameComponent getComponentWithTag(String tag)
	{
		for (GameComponent c : components)
		{
			if (c.getTag().equals(tag))
			{
				return c;			
			}
		}
		
		return null;
	}
	
	/**
	 * Gets a list of all the components which match the given tag
	 * The list contains only the first found component with each given tag
	 * meaning the maximum size of the returned list is tags.length
	 * 
	 * @param tags the tags to check
	 * @return the components that match the tags
	 */
	public ArrayList<GameComponent> getComponentsWithTags(String... tags)
	{
		ArrayList<GameComponent> result = new ArrayList<GameComponent>();
		
		for (GameComponent c : components)
		{
			for (String t : tags)
			{
				if (c.getTag().equals(t)) 
				{
					result.add(c); 
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Finds the first child that has the given tag
	 * 
	 * @param tag the tag to check against
	 * @return the matching child
	 */
	public GameObject getChildWithTag(String tag)
	{
		for (GameObject c : children)
		{
			if (c.getTag().equals(tag))
			{
				return c;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets a list of all the children which match the given tag
	 * The list contains only the first found child with each given tag
	 * meaning the maximum size of the returned list is tags.length
	 * 
	 * @param tags the tags to check
	 * @return the children that match the tags
	 */
	public ArrayList<GameObject> getChildrenWithTags(String... tags)
	{
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		
		for (GameObject c : children)
		{
			for (String t : tags)
			{
				if (c.getTag().equals(t)) 
				{
					result.add(c); 
					break;
				}
			}
		}
		
		return result;
	}
	
}
