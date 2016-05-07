package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import engine.core.GameObject;

public class RenderingEngine
{
	public RenderingEngine()
	{
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //TODO: add way to set clear color
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public void render(GameObject obj)
	{
		glClear(GL_COLOR_BUFFER_BIT);
		
		obj.render(this);
		obj.renderAll(this);
	}
}
