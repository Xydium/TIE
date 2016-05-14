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
		this(new Rectangle(size), texture);
	}

	public RectRenderer(Rectangle rect, Texture texture)
	{
		setShader(new Shader("basic-shader"));
		this.rect = rect;
		this.texture = texture;
		uniformConfig = null;
	}

	public void render()
	{
		texture.bind();
		getShader().bind();

		if (uniformConfig != null)
		{
			uniformConfig.setUniforms(getShader());
		}
		
		getApplication().getRenderingEngine().updateOverlayBrightness(getShader());
		
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

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	
	public void setShader(Shader shader)
	{
		this.shader = shader;
	}
	
	public Texture getTexture()
	{
		return texture;
	}

	public Shader getShader()
	{
		return shader;
	}
	
	public void setUniformConfig(UniformConfig uniformConfig)
	{
		this.uniformConfig = uniformConfig;
	}

	public interface UniformConfig
	{
		public void setUniforms(Shader s);
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
