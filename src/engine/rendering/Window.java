package engine.rendering;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Static window class for wrapping LWJGL 2's Display class
 * 
 * @author Lenny Litvak
 *
 */
public final class Window
{
	private static WindowFlags flags;
	
	/**
	 * Creates a new window with the given title and dimensions
	 * 
	 * @param title the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public static void create(String title, int width, int height)
	{
		create(new WindowFlags(title, width, height));
	}
	
	/**
	 * Creates a new window with the given window flags
	 * 
	 * @param flags the flags for creating the window
	 */
	public static void create(WindowFlags flags)
	{
		Window.flags = flags;
		
		Display.setTitle(flags.getTitle());
		
		try
		{
			Display.setDisplayMode(new DisplayMode(flags.getWidth(), flags.getHeight()));
			Display.create();
			Keyboard.create();
			Mouse.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Renders everything drawn to the window to the screen
	 */
	public static void render()
	{
		Display.update();
	}
	
	/**
	 * Checks whether the window requested to close
	 * 
	 * @return if the window requested to close
	 */
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	/**
	 * Cleans up the window, keyboard, and mouse
	 */
	public static void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	/**
	 * Gets the width of the window
	 * 
	 * @return the window's width
	 */
	public static int getWidth()
	{
		return flags.getWidth();
	}
	
	/**
	 * Gets the height of the window
	 *  
	 * @return the window's height
	 */
	public static int getHeight()
	{
		return flags.getHeight();
	}
	
	/**
	 * Gets the aspect ratio (width / height) of the window
	 * 
	 * @return the aspect ratio
	 */
	public static double getAspectRatio()
	{
		return (double)flags.getWidth() / (double)flags.getHeight();
	}
	
	/**
	 * Gets the title of the window
	 * 
	 * @return the window's title
	 */
	public static String getTitle()
	{
		return flags.getTitle();
	}
	
	/**
	 * Gets the window flags used to create the window
	 * 
	 * @return the window flags
	 */
	public static WindowFlags getFlags()
	{
		return flags;
	}
}
