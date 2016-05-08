package engine.core;

public class Scene
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
