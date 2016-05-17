package engine.rendering;

import java.util.HashMap;

import engine.math.Vector2;
import engine.resmgmt.VertexBufferResource;
import engine.utility.Log;
import engine.utility.Util;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class VertexBuffer
{
	private static HashMap<String, VertexBufferResource> loadedVBResources
		= new HashMap<String, VertexBufferResource>();
	
	private VertexBufferResource resource;
	private String key;
	
	public static VertexBuffer createSquare()
	{
		VertexBufferResource out = loadedVBResources.get("square");
		
		if (out == null)
		{
			Vertex[] vertices = {new Vertex(new Vector2(-1, -1), new Vector2(0, 1)),
				new Vertex(new Vector2(-1, 1), new Vector2(0, 0)),
				new Vertex(new Vector2(1, 1), new Vector2(1, 0)),
				new Vertex(new Vector2(1, -1), new Vector2(1, 1))};
			int[] indices = {0, 1, 2, 3};
			
			out = genVBResource(vertices, indices);
			loadedVBResources.put("square", out);
		}
		
		return new VertexBuffer("square", out);
	}
	
	public VertexBuffer(String key)
	{
		this.key = key;
		
		resource = loadedVBResources.get(key);
		
		if (resource == null)
		{
			Log.error("Invalid VB resource loaded");
		}
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
	
	private VertexBuffer(String key, VertexBufferResource vbr)
	{
		this.key = key;
		this.resource = vbr;
	}
	
	public void render()
	{
		final int FLOAT_SIZE = 4;
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVBO());
		glVertexAttribPointer(0, 2, GL_FLOAT, false, Vertex.SIZE * FLOAT_SIZE, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * FLOAT_SIZE, 2 * FLOAT_SIZE);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIBO());
		glDrawElements(GL_QUADS, resource.getSize(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	}
	
	public String getKey()
	{
		return key;
	}
	
	private static VertexBufferResource genVBResource(Vertex[] vertices, int[] indices)
	{
		VertexBufferResource out = new VertexBufferResource(vertices.length);
		
		glBindBuffer(GL_ARRAY_BUFFER, out.getVBO());
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, out.getIBO());
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
		
		return out;
	}
}
