package test;

import static org.lwjgl.opengl.GL11.*;
import engine.core.Game;
import engine.core.Input;
import engine.rendering.Shader;
import engine.rendering.Texture;

public class TestGame extends Game
{
	private Texture myTexture;
	private Shader myShader;
	
	public void start()
	{
		myTexture = new Texture("test.png");
		myShader = new Shader("testShader");
	}
	
	public void input()
	{
		if (Input.getKeyDown(Input.KEY_S))
		{
			System.out.println("S down");
		}
		else if (Input.getKeyUp(Input.KEY_S))
		{
			System.out.println("S up");
		}
	}
	
	public void render()
	{
		myTexture.bind();
		myShader.bind();
		
		glBegin(GL_QUADS);
		
		glVertex2f(-1.0f, -1.0f);
		glTexCoord2f(0.0f, 1.0f);
		
		glVertex2f(-1.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f);
		
		glVertex2f(1.0f, 0.5f);
		glTexCoord2f(1.0f, 0.0f);
		
		glVertex2f(1.0f, -0.5f);
		glTexCoord2f(1.0f, 1.0f);
		
		glEnd();
	}
}
