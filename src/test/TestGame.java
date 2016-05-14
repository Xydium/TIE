package test;

import engine.components.PointLight;
import engine.components.RectRenderer;
import engine.components.RectRenderer.UniformConfig;
import engine.core.Game;
import engine.core.GameObject;
import engine.core.Input;
import engine.math.Mathf;
import engine.math.Vector2;
import engine.rendering.Color;
import engine.rendering.Shader;
import engine.rendering.Texture;
import engine.utility.Log;

public class TestGame extends Game
{
	private Shader myShader;
	
	private GameObject obj, obj2, obj3;
	
	private PointLight light;
	
	private float counter;
	
	public void start()
	{
		myShader = new Shader("color-shader");
		
		obj = new GameObject();
		final RectRenderer rr = new RectRenderer(new Vector2(1f, 1f), new Texture("test.png"));
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
		
		obj3 = new GameObject();
		light = new PointLight(0.25f);
		light.setBrightness(1);
		getApplication().getRenderingEngine().addLight(light);
		obj3.addComponent(light);
		obj3.getTransform().setPosition(0.25f, 0.25f);
		
		GameObject obj4 = new GameObject();
		PointLight light2 = new PointLight(0.5f);
		getApplication().getRenderingEngine().addLight(light2);
		obj4.addComponent(light2);
		obj4.getTransform().setPosition(-0.25f, -0.25f);
		
		counter = 0.0f;
		
		rr.setUniformConfig(new UniformConfig()
		{
			public void setUniforms(Shader s)
			{
				s.setUniform("time", counter);
				s.setUniform("frequency", 5.0f);
				s.setUniform("amplitude", 0.01f);
			}
		});
		
		add(obj);
		add(obj2);
		add(obj3);
		add(obj4);
		
		Log.info("Initialized");
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
		//obj3.getTransform().setPosition(0, Mathf.sin(counter));
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
