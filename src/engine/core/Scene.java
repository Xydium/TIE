package engine.core;

public abstract class Scene
{
	protected GameObject rootObject;
	
	public Scene()
	{
		rootObject = new GameObject();
	}
	
	public GameObject getRootObject()
	{
		return rootObject;
	}
}
