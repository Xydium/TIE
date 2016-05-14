package engine.text;

import org.lwjgl.opengl.GL11;

public class TextRenderer {

	public static void renderMessage(Message message) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glColor3f(0.5f,0.5f,1.0f);
		 
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(100,100);
		    GL11.glVertex2f(100+200,100);
		    GL11.glVertex2f(100+200,100+200);
		    GL11.glVertex2f(100,100+200);
		GL11.glEnd();
	}
	
}
