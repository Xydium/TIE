package engine.components;

import engine.core.GameComponent;
import engine.math.Vector2;
import engine.rendering.Rectangle;
import engine.rendering.Shader;
import engine.rendering.Texture;

public class RectRenderer extends GameComponent
{
	private Texture texture;
	private Shader shader;

	private Rectangle rect;
	
	private UniformConfig uniformConfig;
	
	public RectRenderer(Vector2 size, Texture texture)
	{
		shader = new Shader("basic-shader");
		rect = new Rectangle(size);
		this.texture = texture;
		uniformConfig = null;
	}
	
	public RectRenderer(Rectangle rect, Texture texture)
	{
		shader = new Shader("basic-shader");
		this.rect = rect;
		this.texture = texture;
		uniformConfig = null;
	}
	
	public void render()
	{
		texture.bind();
		shader.bind();
		
		if (uniformConfig != null)
		{
			uniformConfig.setUniforms();
		}
		
		rect.render(getTransform());
	}
	
	public Rectangle getRect()
	{
		return rect;
	}
	
	public void setSize(Vector2 size)
	{
		rect.setSize(size);
	}
	
	public void setShader(Shader shader)
	{
		this.shader = shader;
	}
	
	public Shader getShader()
	{
		return shader;
	}
	
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public void setUniformConfig(UniformConfig uniformConfig)
	{
		this.uniformConfig = uniformConfig;
	}
	
	public interface UniformConfig
	{
		public void setUniforms();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RectRenderer [texture=" + texture + ", shader=" + shader + ", rect=" + rect + ", uniformConfig="
				+ uniformConfig + "]";
	}
}
