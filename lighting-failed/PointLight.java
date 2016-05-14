package engine.components;

import engine.components.Renderable;
import engine.rendering.Shader;

public class PointLight extends BaseLight
{
	private float range;
	
	public PointLight(float range)
	{
		this.range = range;
	}
	
	public void setRange(float range)
	{
		this.range = range;
	}
	
	public float getRange()
	{
		return range;
	}

	public void applyLight(Renderable renderable)
	{
		Shader s = renderable.getShader();
		s.setUniform("lighting", renderable.getRenderLighting());
		
		if (renderable.getRenderLighting())
		{
			s.setUniform("windowSize", renderable.getApplication().getWindowFlags().getDimensions());
			s.setUniform("lightPos", getTransform().getGlobalPosition());
			s.setUniform("range", range);
			s.setUniform("brightness", getBrightness());
		}
	}
}
