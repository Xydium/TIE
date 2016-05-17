package engine.utility;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import engine.math.Vector2f;
import engine.math.Vector2i;
import engine.rendering.Color;
import engine.rendering.Vertex;
import engine.rendering.Window;


/**
 * Miscellaneous collection of utility functions with
 * no better location
 * 
 * @author Lenny Livak
 * @author Tim Hornick
 *
 */
public class Util 
{

	/**
	 * Converts pixel coordinates starting at the top left (0, 0) of the screen
	 * to OpenGL's window coordinates which go -1 to 1 from the center
	 * 
	 * @param coord the coordinate position to convert
	 * @return the window coordinate
	 */
	public static Vector2f pixelCoordToWindow(Vector2i coord) 
	{
		float halfWidth = Window.getWidth() / 2.f;
		float halfHeight = Window.getHeight() / 2.f;

		//pixel.setY(pixel.getY() / Window.getHeight() * (1 / (float) Window.getAspectRatio()) - 1 / (float) Window.getAspectRatio());
		
		return new Vector2f(coord.getX() / halfWidth - 1.f, -(coord.getY() / halfHeight - 1.f));
	}
	
	/**
	 * 
	 * @param dim
	 * @return
	 */
	public static Vector2f pixelDimensionToWindow(Vector2i dim)
	{
		float halfWidth = Window.getWidth() / 2.f;
		float halfHeight = Window.getHeight() / 2.f;
		
		return new Vector2f(dim.getX() / halfWidth, dim.getY() / halfHeight);
	}
	
	/**
	 * @param pixel
	 * @return modified vector as GL dimensions
	 */
	/*public static Vector2f pixelDToGL(Vector2f pixel) 
	{
		pixel.setX(pixel.getX() / Window.getWidth());
		pixel.setY(pixel.getY() / Window.getWidth());
		return pixel;
	}*/
	
	/**
	 * Creates a buffer of bytes with the given size
	 * 
	 * @param size the size of the byte buffer
	 * @return the new byte buffer
	 */
	public static ByteBuffer createByteBuffer(int size)
	{
		return BufferUtils.createByteBuffer(size);
	}
	
	/**
	 * Creates a buffer of ints with the given size
	 * 
	 * @param size the size of the int buffer
	 * @return the new int buffer
	 */
	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}
	
	/**
	 * Creates a buffer of floats with the given size
	 * 
	 * @param size the size of the float buffer
	 * @return the new float buffer
	 */
	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}
	
	/**
	 * Creates a flipped int buffer from the given set of values
	 * 
	 * @param values the values to insert in the buffer
	 * @return the flipped IntBuffer
	 */
	public static IntBuffer createFlippedBuffer(int... values)
	{
		IntBuffer buffer = createIntBuffer(values.length);
		
		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
	{
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
		
		for(int i = 0; i < vertices.length; i++)
		{
			buffer.put(vertices[i].getPosition().getX());
			buffer.put(vertices[i].getPosition().getY());
			
			buffer.put(vertices[i].getTexCoord().getX());
			buffer.put(vertices[i].getTexCoord().getY());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	/**
	 * Converts an array of java Integer types to an array of
	 * java int type
	 * 
	 * @param data the Integer data
	 * @return the int copy of the given data
	 */
	public static int[] toIntArray(Integer[] data)
	{
		int[] result = new int[data.length];
		
		for (int i = 0; i < data.length; i++)
			result[i] = data[i].intValue();
		
		return result;
	}
	
	/**
	 * Converts the internal engine color class's data to a 
	 * Java AWT color object
	 * 
	 * @param color the input engine.rendering.Color object
	 * @return the Java AWT color object
	 */
	public static java.awt.Color colorToAwt(Color color)
	{
		float red = color.getR();
		float blue = color.getB();
		float green = color.getG();
		float alpha = color.getA();
		
		return new java.awt.Color(red, blue, green, alpha);
	}
}
