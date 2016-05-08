package engine.rendering;

import engine.math.Vector2;

public class Vertex
{
	private Vector2 position;
	private Vector2 texCoord;
	
	public Vertex(Vector2 position, Vector2 texCoord)
	{
		this.position = position;
		this.texCoord = texCoord;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public Vector2 getTexCoord()
	{
		return texCoord;
	}
}
