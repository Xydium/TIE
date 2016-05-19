package engine.core;

import engine.utility.Log;

/**
 * Game which is handled by the game engine
 * 
 * @author Lenny Litvak
 *
 */
public abstract class Game
{
	private Application application;
	private Scene scene;
	
	/**
	 * Creates a new object of type Game
	 * and creates a new Scene
	 */
	public Game()
	{
		setScene(new Scene());
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
	 * Updates the input on all objects in the scene
	 */
	public void inputScene()
	{
		getScene().input();
		getScene().getRootObject().input();
		getScene().getRootObject().inputAll();
	}
	
	/**
	 * Updates all objects in the scene
	 */
	public void updateScene()
	{
		getScene().update();
		getScene().getRootObject().update();
		getScene().getRootObject().updateAll();
	}
	
	/**
	 * Updates all objects in the scene
	 */
	public void lateUpdateScene()
	{
		getScene().lateUpdate();
		//getScene().getRootObject().update();
		//getScene().getRootObject().updateAll();
	}
	
	/**
	 * Renders all objects in the scene
	 */
	public void renderScene()
	{
		getScene().render();
		getScene().getRootObject().render();
		getScene().getRootObject().renderAll();
	}
	
	/**
	 * Adds the given object to the current scene
	 * 
	 * @param obj the object to add
	 */
	public void add(GameObject obj)
	{
		getScene().add(obj);
	}
	
	/**
	 * Adds all of the given objects to the current scene
	 * 
	 * @param objects the objects to add
	 */
	public void addAll(GameObject... objects)
	{
		getScene().addAll(objects);
	}
	
	/**
	 * Sets the current scene of the game to be the given scene
	 * AND calls scene.init()
	 * 
	 * @param scene the scene to be added
	 */
	public void setScene(Scene scene)
	{
		Log.internal("Changed scene to " + scene.getClass().getSimpleName());
		scene.deactivate();
		this.scene = scene;
		scene.setApplication(application);
		
		if (!scene.isLoaded())
		{
			scene.setLoaded(true);
			scene.load();
		}
			
		scene.activate();
	}
	
	/**
	 * Gets the current scene of the game
	 * 
	 * @return the game's current scene
	 */
	public Scene getScene()
	{
		return scene;
	}
	
	/**
	 * Sets the application to which this game belongs
	 * 
	 * @param application the game's application
	 */
	public void setApplication(Application application)
	{
		this.application = application;
		getScene().setApplication(application);
	}
	
	/**
	 * Gets the application to which this game belongs
	 * 
	 * @return the game's application
	 */
	public Application getApplication()
	{
		return application;
	}
}
