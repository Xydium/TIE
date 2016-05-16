package engine.rendering;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import engine.utility.Log;

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

		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAtrributes = new ContextAttribs(3, 2).withProfileCore(true);

		try
		{
		    Display.setDisplayMode(new DisplayMode(flags.getWidth(), flags.getHeight()));
		    Display.setTitle(flags.getTitle());
		    
		    if (!flags.getCornerIcon().equals("") && !flags.getTaskbarIcon().equals(""))
		    {
		    	setIcons(flags.getCornerIcon(), flags.getTaskbarIcon());
		    }
		    
		    Display.create(pixelFormat, contextAtrributes);
		    Keyboard.create();
		    Mouse.create();
		    Log.info("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		    Log.internal("Display Created");
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			Log.error(e);
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
	 * Sets the taskbar and corner icons of the window
	 * the corner icon must be 16x16
	 * and the taskbar icon must be 32x32
	 * 
	 * @param cornerIcon the corner icon name
	 * @param taskbarIcon the taskbar icon name
	 */
	public static void setIcons(String cornerIcon, String taskbarIcon)
	{
		final String BASE_PATH = "/assets/textures/";

		try
		{
			ByteBuffer[] data = new ByteBuffer[2];
			data[0] = Texture.loadImageToByteBuffer(ImageIO.read(Window.class.getResourceAsStream(BASE_PATH + cornerIcon)));
			data[1] = Texture.loadImageToByteBuffer(ImageIO.read(Window.class.getResourceAsStream(BASE_PATH + taskbarIcon)));
			Display.setIcon(data);
		}
		catch (IOException e)
		{
		}
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
