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
	private boolean loaded;
	
	/**
	 * Creates a new scene with its own root object
	 */
	public Scene()
	{
		loaded = false;
		rootObject = new GameObject();
	}
	
	/**
	 * Called when the scene is loaded (set as a game's current scene for the first time)
	 */
	public void load()
	{
	}
	
	/**
	 * Called when the scene is set as a game's current scene
	 */
	public void activate()
	{	
	}
	
	/**
	 * Called when the scene is no longer set as a game's current scene
	 */
	public void deactivate()
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
	 * Called every update frame
	 */
	public void lateUpdate()
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
	 * Cleans up all removed stuff from the scene
	 */
	public void cleanRemoved()
	{
		rootObject.cleanRemovedObjects();
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
	 * Sets whether the scene was loaded
	 * should not be called
	 * 
	 * @param loaded whether the scene was loaded
	 */
	public void setLoaded(boolean loaded)
	{
		this.loaded = loaded;
	}
	
	/**
	 * Whether the scene was loaded or not
	 * 
	 * @return whether the scene was loaded
	 */
	public boolean isLoaded()
	{
		return loaded;
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
