package engine.rendering;

import engine.math.Vector2f;
import engine.utility.Log;
import engine.utility.Log.LogLevel;

/**
 * Flags for the creation of a window, meant to be
 * given to the Window class for window creation
 * 
 * @author Lenny Litvak
 *
 */
public class WindowFlags
{
	private String title;
	
	private int width;
	private int height;
	
	private String cornerIcon;
	private String taskbarIcon;
	
	/**
	 * Creates a new window flags object with
	 * the title "Window", a width of 800, and a height of 600
	 */
	public WindowFlags()
	{
		this("Window", 800, 600);
	}
	
	/**
	 * Creates a new window flags object with the given
	 * title, width, and height
	 * 
	 * @param title the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public WindowFlags(String title, int width, int height)
	{
		this(title, width, height, LogLevel.NONE, false);
	}
	
	/**
	 * Creates a new window flags object with the given
	 * title, width, height, log-level, and whether the console
	 * should be enabled
	 * 
	 * @param title the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 * @param logLevel the level of the output log
	 * @param consoleEnabled whether the log should output into the console
	 */
	public WindowFlags(String title, int width, int height, LogLevel logLevel, boolean consoleEnabled) 
	{
		this.title = title;
		this.width = width;
		this.height = height;
		
		cornerIcon = "";
		taskbarIcon = "";
		
		Log.setLogLevel(logLevel);
		Log.setConsoleEnabled(consoleEnabled);
	}
	
	/**
	 * Sets the title of the window flags
	 * 
	 * @param title the new title of the window flags
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Sets the width of the window flags
	 * 
	 * @param width the new width of the window flags
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	/**
	 * Sets the height of the window flags
	 * 
	 * @param height the new height of the window flags
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	/**
	 * Sets the dimensions (width and height) of the window flags
	 * 
	 * @param dimensions the new dimensions of the window flags
	 */
	public void setDimensions(Vector2f dimensions)
	{
		width = (int)dimensions.getX();
		height = (int)dimensions.getY();
	}
	
	/**
	 * Gets the dimensions of the window flags
	 * 
	 * @return the dimensions of the window flags
	 */
	public Vector2f getDimensions()
	{
		return new Vector2f(width, height);
	}
	
	/**
	 * Sets the maximum level of log output to write to the log
	 * 
	 * @param logLevel the maximum log level
	 */
	public void setLogLevel(LogLevel logLevel) 
	{
		Log.setLogLevel(logLevel);
	}
	
	/**
	 * Sets whether the log should output to the console
	 * 
	 * @param consoleEnabled whether the log should output to the console
	 */
	public void setConsoleEnabled(boolean consoleEnabled) 
	{
		Log.setConsoleEnabled(consoleEnabled);
	}
	
	/**
	 * Sets the icon file names for the corner icon and the taskbar icon
	 * of the window
	 * 
	 * @param cornerIcon the corner icon file name
	 * @param taskbarIcon the taskbar icon file name
	 */
	public void setIconFiles(String cornerIcon, String taskbarIcon)
	{
		this.cornerIcon = cornerIcon;
		this.taskbarIcon = taskbarIcon;
	}
	
	/**
	 * Gets the title of the window flags
	 * 
	 * @return the window flags' title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Gets the width of the window flags
	 * 
	 * @return the window flags' width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Gets the height of the window flags
	 * 
	 * @return the window flags' height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Gets the file name for the corner icon
	 * 
	 * @return the corner icon file name
	 */
	public String getCornerIcon()
	{
		return cornerIcon;
	}
	
	/**
	 * Gets the file name for the taskbar icon
	 * 
	 * @return the taskbar icon file name
	 */
	public String getTaskbarIcon()
	{
		return taskbarIcon;
	}
	
	/**
	 * Gets the log level of the log
	 * 
	 * @return the log's log level
	 */
	public LogLevel getLogLevel()
	{
		return Log.getLogLevel();
	}
	
	/**
	 * Gets whether the log should output to the console
	 * 
	 * @return whether the log should output to the console
	 */
	public boolean getConsoleEnabled()
	{
		return Log.getConsoleEnabled();
	}
}
