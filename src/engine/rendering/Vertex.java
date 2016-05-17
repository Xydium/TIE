package engine.rendering;

import engine.math.Vector2f;

/**
 * Object for storing the position and texture coordinates of
 * a vertex in 2D space
 * 
 * @author Lenny Litvak
 *
 */
public class Vertex
{
	public static final int SIZE = 4;
	
	private Vector2f position;
	private Vector2f texCoord;
	
	/**
	 * Creates a new Vertex object with the given
	 * position and texture coordinate
	 * 
	 * @param position
	 * @param texCoord
	 */
	public Vertex(Vector2f position, Vector2f texCoord)
	{
		this.position = position;
		this.texCoord = texCoord;
	}
	
	/**
	 * Gets the position of the vertex
	 * 
	 * @return the vertex position
	 */
	public Vector2f getPosition()
	{
		return position;
	}
	
	/**
	 * Gets the texture coordinate of the vertex
	 * 
	 * @return the vertex texcoord
	 */
	public Vector2f getTexCoord()
	{
		return texCoord;
	}
}
