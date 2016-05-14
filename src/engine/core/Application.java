package engine.core;

import engine.audio.GlobalAudio;
import engine.physics.Collider;
import engine.rendering.RenderingEngine;
import engine.rendering.Window;
import engine.rendering.WindowFlags;
import engine.utility.Time;

/**
 * An application which creates a window for and
 * runs a game
 * 
 * @author Lenny Litvak
 *
 */
public class Application
{
	private Game game;
	private RenderingEngine renderingEngine;
	private WindowFlags windowFlags;
	
	private boolean isRunning;
	private double deltaTime;
	private double frameTime;
	
	private int lastFPS;
	
	/**
	 * Creates a new Application object with a given game,
	 * frame rate, and window creation flags
	 * 
	 * @param game The game this application will run
	 * @param framesPerSecond The frame rate of the application
	 * @param windowFlags flags for the creation of the application window
	 */
	public Application(Game game, double framesPerSecond, WindowFlags windowFlags)
	{
		this.game = game;
		this.windowFlags = windowFlags;
		game.setApplication(this);
		
		frameTime = 1.0 / framesPerSecond;
		
		isRunning = false;
		deltaTime = 0.0;
		
		Window.create(windowFlags);
		renderingEngine = new RenderingEngine(this);
		
		GlobalAudio.initAudio();
	}
	
	/**
	 * Starts the game engine loop
	 * Will not restart if the game engine is already running
	 */
	public void start()
	{
		if (isRunning)
		{
			return;
		}
		
		isRunning = true;
		
		run();
	}
	
	/**
	 * Stops the game engine loop
	 */
	public void stop()
	{
		if (!isRunning)
		{
			return;
		}
		
		isRunning = false;
	}
	
	private void run()
	{
		int frames = 0;
		double frameCounter = 0.0;
		
		double lastTime = Time.getTime();
		double currentTime;
		double unprocessedTime = 0.0;
		
		boolean render;
		
		game.start();
		
		while (isRunning)
		{
			render = false;
			
			currentTime = Time.getTime();
			deltaTime = currentTime - lastTime;
			lastTime = currentTime;
			
			unprocessedTime += deltaTime;
			frameCounter += deltaTime;
			
			while (unprocessedTime > frameTime)
			{
				render = true;
				
				unprocessedTime -= frameTime;
				
				if (Window.isCloseRequested())
				{
					stop();
				}
				
				game.input();
				game.inputScene();
				
				Input.update();
				
				game.update();
				game.updateScene();
				
				Collider.resolveCollisions();
				
				if (frameCounter >= 1.0)
				{
					lastFPS = frames;
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if (render)
			{
				renderingEngine.clear();
				game.render();
				game.renderScene();
				Window.render();
				++frames;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		cleanUp();
	}
	
	private void cleanUp()
	{
		Window.dispose();
	}
	
	/**
	 * Gets the rendering engine of the application
	 * 
	 * @return the rendering engine
	 */
	public RenderingEngine getRenderingEngine()
	{
		return renderingEngine;
	}
	
	/**
	 * Gets the delta-time of the last frame passed
	 * of the engine
	 * 
	 * @return delta time
	 */
	public double getDeltaTime()
	{
		return deltaTime;
	}
	
	/**
	 * Gets the window flags used to create the app window
	 * 
	 * @return window flags
	 */
	public WindowFlags getWindowFlags()
	{
		return windowFlags;
	}
	
	/**
	 * Gets the game the application is running
	 * 
	 * @return the application's game
	 */
	public Game getGame() 
	{
		return game;
	}
	
	/**
	 * Gets the last FPS of the application
	 * 
	 * @return the last fps
	 */
	public int getLastFPS()
	{
		return lastFPS;
	}
	
}
