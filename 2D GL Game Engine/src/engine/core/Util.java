package engine.core;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public final class Util
{
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
