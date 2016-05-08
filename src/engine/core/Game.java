package engine.core;

/**
 * Game which is handled by the game engine
 * 
 * @author Lenny Litvak
 *
 */
public abstract class Game
{
	private GameObject rootObject;
	
	/**
	 * Creates a new object of type Game
	 */
	public Game()
	{
		rootObject = new GameObject();
	}
	
	/**
	 * Called when the application is initialized
	 */
	public void init()
	{
	}
	
	/**
	 * Called when the game starts
	 */
	public void start()
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
	 * Called every time the game renders
	 */
	public void render()
	{
	}
	
	/**
	 * Updates the input on all objects in the game
	 */
	public void inputObjects()
	{
		rootObject.input();
		rootObject.inputAll();
	}
	
	/**
	 * Updates all objects in the game
	 */
	public void updateObjects()
	{
		rootObject.update();
		rootObject.updateAll();
	}
	
	/**
	 * Renders all objects in the game
	 */
	public void renderObjects()
	{
		getApplication().getRenderingEngine().render(rootObject);
	}
	
	/**
	 * Adds the given object to the game
	 * 
	 * @param obj the object to add
	 */
	public void add(GameObject obj)
	{
		rootObject.addChild(obj);
	}
	
	/**
	 * Adds all of the given objects to the game
	 * 
	 * @param objects the objects to add
	 */
	public void addAll(GameObject... objects)
	{
		rootObject.addAllChildren(objects);
	}
	
	/**
	 * Gets the root object of the game
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
	 * @param application the game's application
	 */
	public void setApplication(Application application)
	{
		rootObject.setApplication(application);
	}
	
	/**
	 * Gets the application to which this game belongs
	 * 
	 * @return the game's application
	 */
	public Application getApplication()
	{
		return rootObject.getApplication();
	}
}
