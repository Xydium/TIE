package engine.rendering;

import java.util.HashMap;

import engine.resmgmt.VertexBufferResource;
import engine.utility.Util;
import static org.lwjgl.opengl.GL15.*;

public class VertexBuffer
{
	private static HashMap<String, VertexBufferResource> loadedVBResources
		= new HashMap<String, VertexBufferResource>();
	
	private VertexBufferResource resource;
	private String key;
	
	public VertexBuffer()
	{
		
	}
	
	public VertexBuffer(String key, Vertex[] vertices, int[] indices)
	{
		this.key = key;
		VertexBufferResource oldResource = loadedVBResources.get(key);
		
		if (oldResource == null)
		{
			resource = genVBResource(vertices, indices);
			loadedVBResources.put(key, resource);
		}
		else
		{
			resource = oldResource;
		}
	}
	
	private static VertexBufferResource genVBResource(Vertex[] vertices, int[] indices)
	{
		VertexBufferResource out = new VertexBufferResource(vertices.length);
		
		glBindBuffer(GL_ARRAY_BUFFER, out.getVBO());
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
		
		return out;
	}
}
