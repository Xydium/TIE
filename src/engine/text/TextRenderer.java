package engine.text;

import java.awt.image.BufferedImage;

import engine.components.RectRenderer;
import engine.math.Transform;
import engine.math.Vector2;
import engine.rendering.Rectangle;
import engine.rendering.Texture;
import engine.utility.Util;

public class TextRenderer {

	public static void renderMessage(Message message) {
		float xOff = 0;
		for(BufferedImage bi : message.getLettersTextures()) {
			Rectangle rect = new Rectangle(Util.pixelCToGL(new Vector2(message.getLoc().getX() + xOff, message.getLoc().getY())));
			RectRenderer rectRenderer = new RectRenderer(rect, new Texture(bi));
			Transform trans = new Transform(new Vector2(bi.getWidth(), bi.getHeight()));
			rect.render(trans);
			rectRenderer.render();
			xOff+=bi.getWidth();
		}
	}
	
}
