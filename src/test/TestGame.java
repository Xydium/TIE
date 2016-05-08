package test;

import engine.components.RectRenderer;
import engine.core.Game;
import engine.core.GameObject;
import engine.core.Input;
import engine.math.Vector2;
import engine.physics.AABBCollider;
import engine.rendering.Color;
import engine.rendering.Shader;
import engine.rendering.Texture;

public class TestGame extends Game
{
	private Texture myTexture;
	private Shader myShader;
	
	private GameObject obj, obj2;
	private AABBCollider a1, a2;
	
	private float counter;
	
	public void start()
	{
		myTexture = new Texture("test.png");
		myShader = new Shader("color-shader");
		
		obj = new GameObject();
		RectRenderer rr = new RectRenderer(new Vector2(0.3f, 0.3f), new Texture("test.png"));
		rr.setShader(new Shader("distort-shader"));
		//rr.setShader(myShader);
		obj.addComponent(rr);
		//a1 = new AABBCollider(rr.getRect());
		//obj.addComponent(a1);
		obj.getTransform().setPosition(0f, 0.3f);
		
		obj2 = new GameObject();
		RectRenderer rr2 = new RectRenderer(new Vector2(0.3f, 0.3f), new Texture("test.png"));
		//rr2.setShader(myShader);
		obj2.addComponent(rr2);
		//a2 = new AABBCollider(rr2.getRect());
		//obj2.addComponent(a2);
		
		//GameObject go = new GameObject();
		//go.addComponent(new RectRenderer(new Vector2(1, 1), new Texture("testjpeg.jpg")));
		//add(go);
		//go.getTransform().setRotation((float)Math.PI * 0.05f);
		
		counter = 0.0f;
		
		rr.setUniformConfig(()->{
			rr.getShader().setUniform("time", counter);
			rr.getShader().setUniform("frequency", 5.0f);
			rr.getShader().setUniform("amplitude", 0.01f);
		});
		
		add(obj);
		add(obj2);
		//addAll(obj, obj2);
	}
	
	public void input()
	{
		if (Input.getKey(Input.KEY_W))
		{
			obj.getTransform().translateBy(new Vector2(0, 0.01f));
		}
		
		if (Input.getKey(Input.KEY_A))
		{
			obj.getTransform().translateBy(new Vector2(-0.01f, 0));
		}
		
		if (Input.getKey(Input.KEY_S))
		{
			obj.getTransform().translateBy(new Vector2(0, -0.01f));
		}
		
		if (Input.getKey(Input.KEY_D))
		{
			obj.getTransform().translateBy(new Vector2(0.01f, 0));
		}
	}
	
	public void update()
	{
		counter += 0.02f;
		myShader.setUniform("color", new Color(1, 0, 0, 0.5f));
		//obj.getTransform().translateBy(new Vector2(0, 0.01f));
		//obj.getTransform().rotateBy(0.01f);
		/*if (a1.collidesWith(a2))
		{
			myShader.setUniform("color", new Color(1, 0, 0));
		}
		else
		{
			myShader.setUniform("color", new Color(0, 1, 0));
		}*/
	}
}
