package test;

import engine.components.RectRenderer;
import engine.components.TextRenderer;
import engine.components.UniformConfig;
import engine.core.Game;
import engine.core.GameObject;
import engine.core.Input;
import engine.math.Vector2i;
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
		RectRenderer rr = new RectRenderer(new Vector2i(200, 200), new Texture("test.png"));
		rr.setShader(new Shader("texture-shader"));
		obj.addComponent(rr);
		obj.getTransform().setPosition(100, 100);

		rr.setUniformConfig(new UniformConfig()
		{
			public void setUniforms(Shader s)
			{
				//s.setUniform("color", new Color(1, 0, 0));
				s.setUniform("time", counter);
				s.setUniform("frequency", 5.0f);
				s.setUniform("amplitude", 0.01f);
			}
		});
		
		GameObject obj2 = new GameObject();
		TextRenderer tr = new TextRenderer("Some text idk", "Papyrus", 20, new Color(1, 0.5f, 1));
		obj2.addComponent(tr);
		obj2.getTransform().setPosition(250, 250);
		add(obj2);
		
		//obj.getTransform().setPosition(0, 0.5f);
		//obj.getTransform().setRotation(Mathf.toRadians(45));
		
		add(obj);
	}
	
	public void update()
	{
		obj.getTransform().lookAt(Input.getMousePosition());
	}
}
