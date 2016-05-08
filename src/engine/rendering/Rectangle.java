package engine.rendering;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

import engine.math.Transform;
import engine.math.Vector2;

public class Rectangle
{
	private static final Vertex[] BASE_VERTICES =
		{new Vertex(new Vector2(-1, -(float)Window.getAspectRatio()), new Vector2(0, 1)),
		new Vertex(new Vector2(-1, (float)Window.getAspectRatio()), new Vector2(0, 0)),
		new Vertex(new Vector2(1, (float)Window.getAspectRatio()), new Vector2(1, 0)),
		new Vertex(new Vector2(1, -(float)Window.getAspectRatio()), new Vector2(1, 1))};
	
	private Vector2 size;
	
	public Rectangle(Vector2 size)
	{
		this.size = size;
	}
	
	public Rectangle(float width, float height)
	{
		size = new Vector2(width, height);
	}
	
	public void setSize(float width, float height)
	{
		size = new Vector2(width, height);
	}
	
	public void setSize(Vector2 size)
	{
		this.size = size;
	}
	
	public Vector2 getSize()
	{
		return size;
	}
	
	public Vector2 getAdjustedSize()
	{
		return size.mul(new Vector2(1, (float)Window.getAspectRatio()));
	}
	
	public void render(Transform trans)
	{
		glBegin(GL_QUADS);
		
		for (int i = 0; i < BASE_VERTICES.length; i++)
		{
			Vector2 pos = BASE_VERTICES[i].getPosition();
			pos = pos.rotateBy(trans.getRotation()).mul(size).add(trans.getPosition());
			
			glVertex2f(pos.getX(), pos.getY());
			glTexCoord2f(BASE_VERTICES[i].getTexCoord().getX(), BASE_VERTICES[i].getTexCoord().getY());
		}
		
		glEnd();
	}
}
