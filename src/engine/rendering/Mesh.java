package engine.rendering;

import java.util.HashMap;

import engine.math.Vector2f;
import engine.resmgmt.MeshResource;
import engine.utility.Log;

import static org.lwjgl.opengl.GL11.*;

public class Mesh
{
	private static HashMap<String, MeshResource> loadedMeshes
		= new HashMap<String, MeshResource>();
	
	private MeshResource resource;
	private String key;
	
	public static Mesh createSquare()
	{
		MeshResource out = loadedMeshes.get("square");
		
		if (out == null)
		{
			Vertex[] vertices = {new Vertex(new Vector2f(-1, -1), new Vector2f(0, 1)),
				new Vertex(new Vector2f(-1, 1), new Vector2f(0, 0)),
				new Vertex(new Vector2f(1, 1), new Vector2f(1, 0)),
				new Vertex(new Vector2f(1, -1), new Vector2f(1, 1))};
			int[] indices = {0, 1, 2, 3};
			
			out = new MeshResource(vertices, indices);
			loadedMeshes.put("square", out);
		}
		
		return new Mesh("square", out);
	}
	
	public Mesh(String key)
	{
		this.key = key;
		
		resource = loadedMeshes.get(key);
		
		if (resource == null)
		{
			Log.error("Invalid VB resource loaded");
		}
	}
	
	public Mesh(String key, Vertex[] vertices, int[] indices)
	{
		this.key = key;
		MeshResource oldResource = loadedMeshes.get(key);
		
		if (oldResource == null)
		{
			resource = new MeshResource(vertices, indices);
			loadedMeshes.put(key, resource);
		}
		else
		{
			resource = oldResource;
		}
	}
	
	private Mesh(String key, MeshResource vbr)
	{
		this.key = key;
		this.resource = vbr;
	}
	
	public void render()
	{
		glBegin(GL_QUADS);
		
		for (int i = 0; i < resource.getIndices().length; i += 4)
		{
			Vertex v0 = resource.getVertexAtIndex(i);
			Vertex v1 = resource.getVertexAtIndex(i + 1);
			Vertex v2 = resource.getVertexAtIndex(i + 2);
			Vertex v3 = resource.getVertexAtIndex(i + 3);
			
			glVertex2f(v0.getPosition().getX(), v0.getPosition().getY());
			glTexCoord2f(v0.getTexCoord().getX(), v0.getTexCoord().getY());
			
			glVertex2f(v1.getPosition().getX(), v1.getPosition().getY());
			glTexCoord2f(v1.getTexCoord().getX(), v1.getTexCoord().getY());

			glVertex2f(v2.getPosition().getX(), v2.getPosition().getY());
			glTexCoord2f(v2.getTexCoord().getX(), v2.getTexCoord().getY());
			
			glVertex2f(v3.getPosition().getX(), v3.getPosition().getY());
			glTexCoord2f(v3.getTexCoord().getX(), v3.getTexCoord().getY());
		}
		
		glEnd();
	}
	
	public String getKey()
	{
		return key;
	}
}
