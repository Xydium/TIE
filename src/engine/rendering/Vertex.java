package engine.rendering;

import engine.math.Vector2;

/**
 * Object for storing the position and texture coordinates of
 * a vertex in 2D space
 * 
 * @author Lenny Litvak
 *
 */
public class Vertex
{
	private Vector2 position;
	private Vector2 texCoord;
	
	/**
	 * Creates a new Vertex object with the given
	 * position and texture coordinate
	 * 
	 * @param position
	 * @param texCoord
	 */
	public Vertex(Vector2 position, Vector2 texCoord)
	{
		this.position = position;
		this.texCoord = texCoord;
	}
	
	/**
	 * Gets the position of the vertex
	 * 
	 * @return the vertex position
	 */
	public Vector2 getPosition()
	{
		return position;
	}
	
	/**
	 * Gets the texture coordinate of the vertex
	 * 
	 * @return the vertex texcoord
	 */
	public Vector2 getTexCoord()
	{
		return texCoord;
	}
}
