package engine.rendering;

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
	
	public WindowFlags()
	{
		this("Window", 800, 600);
	}
	
	public WindowFlags(String title, int width, int height)
	{
		this(title, width, height, LogLevel.NONE, false);
	}
	
	public WindowFlags(String title, int width, int height, LogLevel logLevel, boolean consoleEnabled) 
	{
		this.title = title;
		this.width = width;
		this.height = height;
		Log.logLevel = logLevel;
		Log.consoleEnabled = consoleEnabled;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public void setLogLevel(LogLevel logLevel) 
	{
		Log.logLevel = logLevel;
	}
	
	public void setConsoleEnabled(boolean consoleEnabled) 
	{
		Log.consoleEnabled = consoleEnabled;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public LogLevel getLogLevel()
	{
		return Log.logLevel;
	}
	
	public boolean getConsoleEnabled()
	{
		return Log.consoleEnabled;
	}
	
}
