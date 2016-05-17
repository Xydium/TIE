package engine.components;

import engine.core.GameComponent;
import engine.math.Vector2;
import engine.rendering.Shader;
import engine.rendering.Texture;
import engine.rendering.VertexBuffer;

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
	private boolean allowLighting;
	
	private Vector2 size;
	private VertexBuffer squareBuffer;

	private UniformConfig uniformConfig;

	/**
	 * Creates a new RectRenderer using the given size
	 * and a given texture
	 * 
	 * Its default shader is the texture-shader
	 * 
	 * @param size the size of the renderer
	 * @param texture the texture to render
	 */
	public RectRenderer(Vector2 size, Texture texture)
	{
		setShader(new Shader("texture-shader"));
		this.size = size;
		this.texture = texture;
		uniformConfig = null;
		allowLighting = true;
		
		squareBuffer = VertexBuffer.createSquare();
	}
	
	/**
	 * Creates a new RectRenderer using the given size
	 * and no texture
	 * 
	 * Its default shader is the texture-shader
	 * 
	 * @param size the size of the renderer
	 */
	public RectRenderer(Vector2 size)
	{
		this(size, null);
	}
	
	/**
	 * Renders the rectangle to the screen with the given texture
	 * and the RectRenderer's current shader
	 */
	public void render()
	{
		getShader().bind();
		
		if (texture != null)
		{
			texture.bind();			
		}
		
		getShader().setUniform("transPosition", getTransform().getGlobalPosition());
		getShader().setUniform("transRotation", getTransform().getGlobalRotation());
		getShader().setUniform("transScale", size);
		
		if (uniformConfig != null)
		{
			uniformConfig.setUniforms(getShader());
		}
		
		squareBuffer.render();
	}
	
	/**
	 * Sets the size of the rectangle
	 * 
	 * @param size the rect's size
	 */
	public void setSize(Vector2 size)
	{
		this.size = size;
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
	 * Sets whether the RectRenderer allows it to be affected
	 * by lighting
	 * 
	 * @param allowLighting whether lighting is allowed
	 */
	public void setAllowLighting(boolean allowLighting)
	{
		this.allowLighting = allowLighting;
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
	 * Gets whether the RectRenderer allows it to be affected
	 * by lighting
	 * 
	 * @return whether lighting is allowed
	 */
	public boolean getAllowLighting()
	{
		return allowLighting;
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
		return "RectRenderer [texture=" + texture + ", shader=" + shader + ", size=" + size + ", uniformConfig="
				+ uniformConfig + "]";
	}
}
