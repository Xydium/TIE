package engine.core;

/**
 * Game scene with its own hierarchy of game objects
 * (aka its own root object)
 * 
 * @author Lenny Litvak
 * @author Tim Hornick
 *
 */
public class Scene
{
	private GameObject rootObject;
	
	/**
	 * Creates a new scene with its own root object
	 */
	public Scene()
	{
		rootObject = new GameObject();
	}
	
	/**
	 * Called when the scene is set as a game's current scene
	 */
	public void init()
	{
	}
	
	/**
	 * Called every frame, after input has been received
	 */
	public void input()
	{
	}
	
	/**
	 * Called every update frame
	 */
	public void update()
	{
	}
	
	/**
	 * Called every time the scene renders
	 */
	public void render()
	{
	}
	
	/**
	 * Adds the given object to the scene
	 * 
	 * @param obj the object to add
	 */
	public void add(GameObject obj)
	{
		rootObject.addChild(obj);
	}
	
	/**
	 * Adds all of the given objects to the scene
	 * 
	 * @param objects the objects to add
	 */
	public void addAll(GameObject... objects)
	{
		rootObject.addAllChildren(objects);
	}
	
	/**
	 * Gets the root object of the scene
	 * 
	 * @return the root object
	 */
	public GameObject getRootObject()
	{
		return rootObject;
	}
	
	/**
	 * Sets the application to which this game belongs
	 * 
	 * @param application the scene's application
	 */
	public void setApplication(Application application)
	{
		rootObject.setApplication(application);
	}
	
	/**
	 * Gets the application to which this scene belongs
	 * 
	 * @return the game's application
	 */
	public Application getApplication()
	{
		return rootObject.getApplication();
	}
}
