package engine.components;

import engine.core.GameComponent;
import engine.rendering.Shader;
import engine.rendering.Texture;

/**
 * Base class for renderer components
 * 
 * @author Lenny Litvak
 *
 */
public abstract class BaseRenderer extends GameComponent
{
	private Texture texture;
	private Shader shader;
	private boolean allowLighting = true;
	
	private UniformConfig uniformConfig;
	
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
	 * Gets the uniform config currently set to
	 * alter the uniforms
	 * 
	 * @return the uniformConfig
	 */
	public UniformConfig getUniformConfig()
	{
		return uniformConfig;
	}
}
