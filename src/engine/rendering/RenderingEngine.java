package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import engine.core.Application;
import engine.math.Mathf;

/**
 * Rendering engine for the game, contains important GL utilities
 * such as screen clearing and lighting management
 * 
 * @author Lenny Litvak
 *
 */
public class RenderingEngine
{
	private Application application;
	
	private Color clearColor;
	private float overlayBrightness;
	private float targetOverlayBrightness = 1;

	/**
	 * Creates a new RenderingEngine object with the given
	 * application as its parent application
	 * 
	 * Sets its clear color to (0, 0, 0, 0) by default
	 * and its overlay brightness to 1.0
	 * 
	 * @param application the rendering engine's application
	 */
	public RenderingEngine(Application application)
	{
		this.application = application;
		
		clearColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
		overlayBrightness = 1.0f;
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_BLEND);
	}
	
	/**
	 * Updates the uniforms for the given shader to use
	 * the current overlay brightness
	 * 
	 * @param shader the shader to update
	 */
	public void updateOverlayBrightness(Shader shader, boolean enabled)
	{
		overlayBrightness = Mathf.lerp(overlayBrightness, targetOverlayBrightness, 0.07f);
		shader.setUniform("lightPercent", enabled ? overlayBrightness : -1.f);
		shader.setUniform("windowSize", application.getWindowFlags().getDimensions());
	}
	
	/**
	 * Sets the overlay brightness of the rendering engine
	 * 
	 * @param overlayBrightness the new overlay brightness
	 */
	public void setOverlayBrightness(float overlayBrightness)
	{
		this.targetOverlayBrightness = overlayBrightness;
	}
	
	/**
	 * Gets the overlay brightness of the rendering engine
	 * 
	 * @return the overlay brightness of the rendering engine
	 */
	public float getOverlayBrightness()
	{
		return overlayBrightness;
	}
	
	/**
	 * Clears the window
	 */
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
