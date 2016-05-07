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
	private float scale;
	
	private float counter;
	
	public void start()
	{
		scale = 1.f; 
		
		counter = 0.0f;
		
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
	
	public void update()
	{
		counter += 0.01f;
		myShader.setUniform("num", counter);
	}
	
	public void render()
	{
		myTexture.bind();
		myShader.bind();
		
		glBegin(GL_QUADS);
		
		glVertex2f(-1.0f * scale, -1.0f * scale);
		glTexCoord2f(0.0f, 1.0f);
		
		glVertex2f(-1.0f * scale, 1.0f * scale);
		glTexCoord2f(0.0f, 0.0f);
		
		glVertex2f(1.0f * scale, 1.0f * scale);
		glTexCoord2f(1.0f, 0.0f);
		
		glVertex2f(1.0f * scale, -1.0f * scale);
		glTexCoord2f(1.0f, 1.0f);
		
		glEnd();
	}
}
