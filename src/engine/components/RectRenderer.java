package engine.components;

import engine.math.Vector2;
import engine.rendering.Rectangle;
import engine.rendering.Shader;
import engine.rendering.Texture;

public class RectRenderer extends Renderable
{
	private Texture texture;
<<<<<<< HEAD
=======
	private Shader shader;

>>>>>>> origin/master
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
			uniformConfig.setUniforms(shader);
		}
		
		//setRenderLighting(false);
		//getApplication().getRenderingEngine().renderLighting(this);
		//rect.render(getTransform());
		//setRenderLighting(true);

		setRenderLighting(false);
		getApplication().getRenderingEngine().renderLightingTemp(this);
		//setRenderLighting(true);
		//getApplication().getRenderingEngine().renderLightingTemp(this);
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
