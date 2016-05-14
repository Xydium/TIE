package engine.resmgmt;

import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;

/**
 * Stores references to GL textures and the number of times
 * they have been referenced
 * 
 * @author Lenny Litvak
 *
 */
public class TextureResource extends Resource
{
	private int id;
	
	/**
	 * Creates a new texture resource object which
	 * allocates a new GL texture buffer
	 */
	public TextureResource()
	{
		id = glGenTextures();
	}
	
	@Override
	protected void finalize()
	{
		glDeleteBuffers(id);
	}
	
	/**
	 * Gets the ID at which the GL texture is allocated
	 * 
	 * @return the GL texture ID
	 */
	public int getID()
	{
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TextureResource [id=" + id + "]";
	}
}
