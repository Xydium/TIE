package engine.components;

import engine.math.Vector2i;
import engine.rendering.Mesh;
import engine.rendering.Shader;
import engine.rendering.Texture;
import engine.utility.Util;

/**
 * A simple component for rendering a rectangle onto the screen
 * with the component's given texture and shader
 * 
 * @author Lenny Litvak
 *
 */
public class RectRenderer extends BaseRenderer
{
	private Vector2i size;
	private Mesh squareMesh;

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
		setTexture(texture);
		
		squareMesh = Mesh.createSquare();
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
		
		if (getTexture() != null)
		{
			getTexture().bind();			
		}
		
		Vector2i halfSize = size.div(2);
		
		getShader().setUniform("trans_Position", Util.pixelCoordToWindow(getTransform().getGlobalPosition().add(halfSize)));
		getShader().setUniform("trans_Rotation", getTransform().getGlobalRotation());
		getShader().setUniform("trans_Scale", Util.pixelDimensionToWindow(halfSize));
		
		if (getUniformConfig() != null)
		{
			getUniformConfig().setUniforms(getShader());
		}
		
		getApplication().getRenderingEngine().updateOverlayBrightness(getShader(), getAllowLighting());
		
		squareMesh.render();
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
		return squareMesh;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RectRenderer [texture=" + getTexture() + ", shader=" + getShader() + ", size=" + size + ", uniformConfig="
				+ getUniformConfig() + "]";
	}
}
