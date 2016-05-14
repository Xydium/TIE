package engine.components;

import engine.core.GameComponent;
import engine.components.Renderable;

public abstract class BaseLight extends GameComponent
{
	private float brightness;
	
	public BaseLight()
	{
		brightness = 1;
	}
	
	public abstract void applyLight(Renderable renderable);
	
	public void setBrightness(float brightness)
	{
		this.brightness = brightness;
	}
	
	public float getBrightness()
	{
		return brightness;
	}
}
