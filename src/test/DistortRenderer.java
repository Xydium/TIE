package test;

import engine.components.RectRenderer;
import engine.math.Vector2;
import engine.rendering.RenderingEngine;
import engine.rendering.Shader;
import engine.rendering.Texture;

public class DistortRenderer extends RectRenderer
{
	private Texture dTexture;
	private Shader shader;
	private float counter;
	
	public DistortRenderer(Vector2 size, Texture texture, Texture dTexture)
	{
		super(size, texture);
		counter = 0;
		shader = new Shader("distort-shader");
		setShader(shader);
		this.dTexture = dTexture;
	}
	
	public void update()
	{
		counter += 0.02f;
		//shader.setUniform("distortionFactor", 0.25f);
		//shader.setUniform("riseFactor", 0.2f);
		shader.setUniform("time", counter);
	}
	
	public void render(RenderingEngine renderingEngine)
	{
		getTexture().bind(0);
		//dTexture.bind(1);
		
		shader.bind();
		
		getRect().render(getTransform());
	}
}
