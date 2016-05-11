package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

import java.nio.ByteBuffer;

import engine.utility.Util;

public class FrameBuffer
{
	private int fbo;
	private int fboTexture;
	
	public FrameBuffer(int width, int height)
	{
		glActiveTexture(GL_TEXTURE0);
		fboTexture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, fboTexture);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		ByteBuffer buffer = Util.createByteBuffer(width * height * 4);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		//https://en.wikibooks.org/wiki/OpenGL_Programming/Post-Processing
	}
}
