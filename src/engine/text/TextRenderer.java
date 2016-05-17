package engine.text;

import java.awt.image.BufferedImage;

import engine.components.RectRenderer;
import engine.math.Transform;
import engine.math.Vector2f;
import engine.rendering.Texture;
import engine.utility.Util;

public class TextRenderer
{

	public static void renderMessage(Message message)
	{
		float xOff = 0;
		for (BufferedImage bi : message.getLettersTextures())
		{
			
			RectRenderer rectRenderer = new RectRenderer(Util.pixelCToGL(new Vector2f(message.getLoc().getX() + xOff, message.getLoc().getY())),
				new Texture(bi));
			Transform trans = new Transform(new Vector2f(bi.getWidth(), bi.getHeight()));
			rectRenderer.render();
			xOff+=bi.getWidth();
		}
	}
	
}
