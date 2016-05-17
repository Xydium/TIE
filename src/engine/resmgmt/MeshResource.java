package engine.resmgmt;

import engine.rendering.Vertex;

public class MeshResource extends Resource
{
	private Vertex[] vertices;
	private int[] indices;
	
	public MeshResource(Vertex[] vertices, int[] indices)
	{
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public Vertex[] getVertices()
	{
		return vertices;
	}
	
	public int[] getIndices()
	{
		return indices;
	}
	
	public Vertex getVertexAtIndex(int i)
	{
		return vertices[indices[i]];
	}
}
