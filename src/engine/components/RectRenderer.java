package engine.components;

import engine.core.GameComponent;
import engine.math.Vector2i;
import engine.rendering.Shader;
import engine.rendering.Texture;
import engine.rendering.Mesh;
import engine.utility.Util;

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
	
	private Vector2i size;
	private Mesh squareBuffer;

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
	public RectRenderer(Vector2i size, Texture texture)
	{
		setShader(new Shader("texture-shader"));
		this.size = size;
		this.texture = texture;
		uniformConfig = null;
		allowLighting = true;
		
		squareBuffer = Mesh.createSquare();
	}
	
	/**
	 * Creates a new RectRenderer using the given size
	 * and no texture
	 * 
	 * Its default shader is the texture-shader
	 * 
	 * @param size the size of the renderer
	 */
	public RectRenderer(Vector2i size)
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
		
		Vector2i halfSize = size.div(2);
		
		getShader().setUniform("trans_Position", Util.pixelCoordToWindow(getTransform().getGlobalPosition().add(halfSize)));
		getShader().setUniform("trans_Rotation", getTransform().getGlobalRotation());
		getShader().setUniform("trans_Scale", Util.pixelDimensionToWindow(halfSize));
		
		if (uniformConfig != null)
		{
			uniformConfig.setUniforms(getShader());
		}
		
		getApplication().getRenderingEngine().updateOverlayBrightness(getShader(), allowLighting);
		
		squareBuffer.render();
	}
	
	/**
	 * Sets the size of the rectangle
	 * 
	 * @param size the rect's size
	 */
	public void setSize(Vector2i size)
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

	/**
	 * Gets the size of the rect renderer
	 * 
	 * @return the size
	 */
	public Vector2i getSize()
	{
		return size;
	}

	/**
	 * Gets the square buffer mesh used to render the rect renderer
	 * 
	 * @return the squareBuffer
	 */
	public Mesh getSquareBuffer()
	{
		return squareBuffer;
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
