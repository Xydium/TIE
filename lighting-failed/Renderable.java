package engine.components;

import engine.core.GameComponent;
import engine.rendering.Shader;

public abstract class Renderable extends GameComponent
{
	private Shader shader;
	private boolean renderLighting;
	
	public void setShader(Shader shader)
	{
		this.shader = shader;
		renderLighting = true;
	}
	
	public void setRenderLighting(boolean renderLighting)
	{
		this.renderLighting = renderLighting;
	}
	
	public Shader getShader()
	{
		return shader;
	}
	
	public boolean getRenderLighting()
	{
		return renderLighting;
	}
}
