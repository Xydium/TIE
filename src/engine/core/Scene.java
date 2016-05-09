package engine.core;

public abstract class Scene
{
	private GameObject rootObject;
	
	public Scene()
	{
		rootObject = new GameObject();
	}
	
	public GameObject getRootObject()
	{
		return rootObject;
	}
}
