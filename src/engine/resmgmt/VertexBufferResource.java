package engine.resmgmt;

import static org.lwjgl.opengl.GL15.*;

public class VertexBufferResource extends Resource
{
	private int vbo;
	private int ibo;
	private int size;
	
	public VertexBufferResource(int size)
	{
		this.size = size;
		
		vbo = glGenBuffers();
		ibo = glGenBuffers();
	}
	
	@Override
	protected void finalize()
	{
		glDeleteBuffers(vbo);
		glDeleteBuffers(ibo);
	}
	
	public int getVBO()
	{
		return vbo;
	}
	
	public int getIBO()
	{
		return ibo;
	}
	
	public int getSize()
	{
		return size;
	}
}
