package engine.components;

import engine.core.GameComponent;
import engine.math.Vector2;
import engine.rendering.Rectangle;
import engine.rendering.Shader;
import engine.rendering.Texture;

/**
 * A simple component for rendering a rectangle onto the screen
 * with the component's given texture and shader
 * 
 * @author Lenny Litvak
 *
 */
public class RectRenderer extends GameComponent
{
	private Texture texture;
	private Shader shader;
	private Rectangle rect;

	private UniformConfig uniformConfig;

	/**
	 * Creates a new RectRenderer using the given size to create the
	 * rectangle and a given texture
	 * 
	 * Its default shader is the texture-shader
	 * 
	 * @param size the size of the rect
	 * @param texture the texture to render
	 */
	public RectRenderer(Vector2 size, Texture texture)
	{
		this(new Rectangle(size), texture);
	}

	/**
	 * Creates a new RectRenderer using the given rectangle and
	 * a given texture
	 * 
	 * Its default shader is the texture-shader
	 * 
	 * @param rect the rect to render
	 * @param texture the texture to render
	 */
	public RectRenderer(Rectangle rect, Texture texture)
	{
		setShader(new Shader("basic-shader"));
		this.rect = rect;
		this.texture = texture;
		uniformConfig = null;
	}

	/**
	 * Renders the rectnagle to the screen with the given texture
	 * and the RectRenderer's current shader
	 */
	public void render()
	{
		if(texture!= null)
		{
			texture.bind();			
		}
		getShader().bind();

		if (uniformConfig != null)
		{
			uniformConfig.setUniforms(getShader());
		}
		
		getApplication().getRenderingEngine().updateOverlayBrightness(getShader());
		
		rect.render(getTransform());
	}

	/**
	 * Gets the rectangle being rendered
	 * 
	 * @return the RectRenderer's rect
	 */
	public Rectangle getRect()
	{
		return rect;
	}

	/**
	 * Sets the size of the rectangle
	 * 
	 * @param size the rect's size
	 */
	public void setSize(Vector2 size)
	{
		rect.setSize(size);
	}

	/**
	 * Sets the texture of the RectRenderer
	 * 
	 * @param texture the new texture
	 */
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	
	/**
	 * Sets the shader of the RectRenderer
	 * 
	 * @param shader the new shader
	 */
	public void setShader(Shader shader)
	{
		this.shader = shader;
	}
	
	/**
	 * Gets the texture of the RectRenderer
	 * 
	 * @return the RectRenderer's texture
	 */
	public Texture getTexture()
	{
		return texture;
	}

	/**
	 * Gets the shader used by the RectRenderer
	 * 
	 * @return the RectRenderer's shader
	 */
	public Shader getShader()
	{
		return shader;
	}
	
	/**
	 * Sets the uniform configuration interface that will be used by the
	 * RectRenderer to set the uniforms of its shader
	 * 
	 * @param uniformConfig the uniform configuration interface
	 */
	public void setUniformConfig(UniformConfig uniformConfig)
	{
		this.uniformConfig = uniformConfig;
	}

	/**
	 * Simple interface that can be used to set the uniforms of the
	 * RectRenderer's shader
	 * 
	 * @author Tim Hornick
	 *
	 */
	public interface UniformConfig
	{
		public void setUniforms(Shader s);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RectRenderer [texture=" + texture + ", shader=" + shader + ", rect=" + rect + ", uniformConfig="
				+ uniformConfig + "]";
	}
}
