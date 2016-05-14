package engine.rendering;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

import engine.math.Transform;
import engine.math.Vector2;

/**
 * Rectangle utility class used to do calculations where a rectangle is
 * required
 * 
 * @author Lenny Litvak
 *
 */
public class Rectangle
{
	private static final Vertex[] BASE_VERTICES =
		{new Vertex(new Vector2(-1, -(float)Window.getAspectRatio()), new Vector2(0, 1)),
		new Vertex(new Vector2(-1, (float)Window.getAspectRatio()), new Vector2(0, 0)),
		new Vertex(new Vector2(1, (float)Window.getAspectRatio()), new Vector2(1, 0)),
		new Vertex(new Vector2(1, -(float)Window.getAspectRatio()), new Vector2(1, 1))};
	
	private Vector2 size;
	
	/**
	 * Creates a new rectangle with the given size
	 * 
	 * @param size the size of the rectangle
	 */
	public Rectangle(Vector2 size)
	{
		this.size = size;
	}
	
	/**
	 * Creates a new rectangle with the given width and height
	 * 
	 * @param width the rectangle's width
	 * @param height the rectangle's height
	 */
	public Rectangle(float width, float height)
	{
		this(new Vector2(width, height));
	}
	
	/**
	 * Sets the size of the rectangle to the given width and height
	 * 
	 * @param width the new width of the rect
	 * @param height the new height of the rect
	 */
	public void setSize(float width, float height)
	{
		size = new Vector2(width, height);
	}
	
	/**
	 * Sets the size of the rectangle to the given size
	 * 
	 * @param size the new size of the rect
	 */
	public void setSize(Vector2 size)
	{
		this.size = size;
	}
	
	/**
	 * Gets the size of the rectangle
	 * 
	 * @return rect's size
	 */
	public Vector2 getSize()
	{
		return size;
	}
	
	/**
	 * Gets the size of the rectangle adjusted to the window's aspect ratio
	 * 
	 * @return rect size adjusted to window's aspect ratio
	 */
	public Vector2 getAdjustedSize()
	{
		return size.mul(new Vector2(1, (float)Window.getAspectRatio()));
	}
	
	/**
	 * Renders the rectangle onto the screen with the given transformation
	 *
	 * @param trans the transformation of the rectangle
	 */
	public void render(Transform trans)
	{
		glBegin(GL_QUADS);
		
		for (int i = 0; i < BASE_VERTICES.length; i++)
		{
			Vector2 pos = BASE_VERTICES[i].getPosition();
			pos = pos.rotateBy(trans.getGlobalRotation()).mul(size).add(trans.getGlobalPosition());
			
			glVertex2f(pos.getX(), pos.getY());
			glTexCoord2f(BASE_VERTICES[i].getTexCoord().getX(), BASE_VERTICES[i].getTexCoord().getY());
		}
		
		glEnd();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Rectangle [size=" + size + "]";
	}
}
