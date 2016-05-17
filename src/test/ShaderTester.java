package test;

import engine.components.RectRenderer;
import engine.components.RectRenderer.UniformConfig;
import engine.core.Game;
import engine.core.GameObject;
import engine.math.Mathf;
import engine.math.Vector2;
import engine.rendering.Color;
import engine.rendering.Shader;
import engine.rendering.Texture;

public class ShaderTester extends Game
{
	GameObject obj;
	
	float counter = 0;
	
	public void start()
	{
		obj = new GameObject();
		RectRenderer rr = new RectRenderer(new Vector2(0.5f, 0.5f), new Texture("test.png"));
		rr.setShader(new Shader("distort-shader"));
		obj.addComponent(rr);
		
		rr.setUniformConfig(new UniformConfig()
		{
			public void setUniforms(Shader s)
			{
				s.setUniform("time", counter);
				s.setUniform("frequency", 5.0f);
				s.setUniform("amplitude", 0.01f);
			}
		});
		
		
		//obj.getTransform().setPosition(0, 0.5f);
		//obj.getTransform().setRotation(Mathf.toRadians(45));
		
		add(obj);
	}
	
	public void update()
	{
		counter += 0.02f;
		obj.getTransform().rotateBy(0.01f);
	}
}
