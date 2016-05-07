package engine.rendering;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Static window class for wrapping LWJGL 2's Display class
 * 
 * @author Lenny
 *
 */
public final class Window
{
	private static WindowFlags flags;
	
	public static void create(String title, int width, int height)
	{
		create(new WindowFlags(title, width, height));
	}
	
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
	
	public static void render()
	{
		Display.update();
	}
	
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public static void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static int getWidth()
	{
		return flags.getWidth();
	}
	
	public static int getHeight()
	{
		return flags.getHeight();
	}
	
	public static String getTitle()
	{
		return flags.getTitle();
	}
	
	public static WindowFlags getFlags()
	{
		return flags;
	}
	
	public static void setFlags(WindowFlags flags)
	{
		Window.flags = flags;
	}
}
