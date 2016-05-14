package engine.rendering;

import static org.lwjgl.opengl.GL11.*;

public class RenderingEngine
{
	private Color clearColor;

	public RenderingEngine()
	{
		clearColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_BLEND);
	}
	
	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Gets the current color being used to clear the screen
	 * 
	 * @return the clear color
	 */
	public Color getClearColor()
	{
		return clearColor;
	}
	
	/**
	 * Sets the color being used to clear the screen
	 * 
	 * @param color the clear color
	 */
	public void setClearColor(Color color)
	{
		this.clearColor = color;
		glClearColor(color.getR(), color.getG(), color.getB(), color.getA());
	}
	
	/**
	 * Gets the current version of OpenGL on the computer
	 * 
	 * @return the OpenGL version
	 */
	public String getGLVersion()
	{
		return glGetString(GL_VERSION);
	}
}
