package engine.components;

import engine.core.GameComponent;
import engine.math.Vector2;
import engine.rendering.Rectangle;
import engine.rendering.RenderingEngine;
import engine.rendering.Shader;
import engine.rendering.Texture;

public class RectRenderer extends GameComponent
{
	private Texture texture;
	private Shader shader;
	private Rectangle rect;
	
	public RectRenderer(Vector2 size, Texture texture)
	{
		shader = new Shader("basic-shader");
		rect = new Rectangle(size);
		this.texture = texture;
	}
	
	public RectRenderer(Rectangle rect, Texture texture)
	{
		shader = new Shader("basic-shader");
		this.rect = rect;
		this.texture = texture;
	}
	
	public void render(RenderingEngine renderingEngine)
	{
		texture.bind();
		shader.bind();
		
		rect.render(getTransform());
	}
	
	public void setSize(Vector2 size)
	{
		rect.setSize(size);
	}
	
	public void setShader(Shader shader)
	{
		this.shader = shader;
	}
	
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
}
