package engine.rendering;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import engine.components.BaseLight;
import engine.components.RectRenderer;
import engine.components.Renderable;

public class RenderingEngine
{
	private Color clearColor;
	private ArrayList<BaseLight> lights;
	
	public RenderingEngine()
	{
		clearColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
		lights = new ArrayList<BaseLight>();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_BLEND);
	}
	
	public void renderLighting(Renderable renderable)
	{
		for (BaseLight light : lights)
		{
			light.applyLight(renderable);
			//System.out.println("Rendering light");
		}
		//System.out.println("-----");
	}
	
	public void renderLightingTemp(RectRenderer renderable)
	{
		for (BaseLight light : lights)
		{
			light.applyLight(renderable);
			renderable.getRect().render(renderable.getTransform());
			//break;
		}
	}
	
	/**
	 * Adds a light to the list of lights in the game
	 * 
	 * @param light the light to add
	 */
	public void addLight(BaseLight light)
	{
		lights.add(light);
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
