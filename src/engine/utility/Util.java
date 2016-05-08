package engine.utility;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import engine.math.Vector2;
import engine.rendering.Window;

/**
 * Useful misfit functions with no real home.
 * 
 * @author Tim Hornick
 *
 */
public class Util 
{

	/**
	 * @param pixel
	 * @return modified vector as GL coords
	 */
	public static Vector2 pixelCToGL(Vector2 pixel) 
	{
		pixel.setX(pixel.getX() / Window.getWidth() * 2 - 1.0f);
		pixel.setY(pixel.getY() / Window.getHeight() / (float) (Window.getAspectRatio() / 2) - 1.0f);
		return pixel;
	}
	
	/**
	 * @param pixel
	 * @return modified vector as GL dimensions
	 */
	public static Vector2 pixelDToGL(Vector2 pixel) 
	{
		pixel.setX(pixel.getX() / Window.getWidth());
		pixel.setY(pixel.getY() / Window.getWidth());
		return pixel;
	}
	
	public static ByteBuffer createByteBuffer(int size)
	{
		return BufferUtils.createByteBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}
	
	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int... values)
	{
		IntBuffer buffer = createIntBuffer(values.length);
		
		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}
	
	public static int[] toIntArray(Integer[] data)
	{
		int[] result = new int[data.length];
		
		for (int i = 0; i < data.length; i++)
			result[i] = data[i].intValue();
		
		return result;
	}
	
}
