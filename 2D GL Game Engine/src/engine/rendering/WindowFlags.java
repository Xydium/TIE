package engine.rendering;

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
		title = "Window";
		width = 800;
		height = 600;
	}
	
	public WindowFlags(String title, int width, int height)
	{
		this.title = title;
		this.width = width;
		this.height = height;
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
}
