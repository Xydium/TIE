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
		application = null;
		tag = "";
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
	 * Adds a child to the game object's children
	 * 
	 * @param child the child to add to the object
	 */
	public void addChild(GameObject child)
	{
		children.add(child);
		child.setApplication(application);
		child.parent = this;
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
	
	public void addComponent(GameComponent component)
	{
		components.add(component);
		component.setParent(this);
	}
	
	public void addAllComponents(GameComponent... components)
	{
		for (GameComponent comp : components)
		{
			addComponent(comp);
		}
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
	
	public String getTag()
	{
		return tag;
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public GameComponent getComponentWithTag(String tag)
	{
		for(GameComponent c : components)
		{
			if(c.getTag().equals(tag)) return c;
		}
		return null;
	}
	
	public ArrayList<GameComponent> getComponentsWithTags(String... tags)
	{
		ArrayList<GameComponent> result = new ArrayList<GameComponent>();
		for(GameComponent c : components)
		{
			for(String t : tags)
			{
				if(c.getTag().equals(t)) 
				{
					result.add(c); 
					break;
				}
			}
		}
		return result;
	}
	
	public GameObject getChildWithTag(String tag)
	{
		for(GameObject c : children)
		{
			if(c.getTag().equals(tag)) return c;
		}
		return null;
	}
	
	public ArrayList<GameObject> getChildrenWithTags(String... tags)
	{
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		for(GameObject c : children)
		{
			for(String t : tags)
			{
				if(c.getTag().equals(t)) 
				{
					result.add(c); 
					break;
				}
			}
		}
		return result;
	}
	
}
