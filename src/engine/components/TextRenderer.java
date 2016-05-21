package engine.components;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import engine.math.Vector2i;
import engine.rendering.Color;
import engine.rendering.Mesh;
import engine.rendering.Shader;
import engine.rendering.Texture;
import engine.utility.Util;

public class TextRenderer extends BaseRenderer
{
	private String text;
	private Font font;
	
	private int width;
	private int height;
	
	private Color color;
	private Mesh squareMesh;
	
	/**
	 * Creates a new TextRenderer using the given
	 * text and font
	 * 
	 * @param text the text to be rendered
	 * @param font the font to render the text with
	 * @param color the color of the text
	 */
	public TextRenderer(String text, Font font, Color color)
	{
		this.text = text;
		this.font = font;
		this.color = color;
		
		setShader(new Shader("color-texture-shader"));
		
		squareMesh = Mesh.createSquare();
		
		genTexture();
	}
	
	/**
	 * Sets the text the TextRenderer renders
	 * 
	 * @param text the text to render
	 */
	public void setText(String text)
	{
		this.text = text;
		genTexture();
	}
	
	/**
	 * Sets the font with which to render the text
	 * 
	 * @param font the font to render the text with
	 */
	public void setFont(Font font)
	{
		this.font = font;
		genTexture();
	}
	
	/**
	 * Gets the text being rendered
	 * 
	 * @return the text being rendered
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Gets the font of the text
	 * 
	 * @return the text font
	 */
	public Font getFont()
	{
		return font;
	}
	
	/**
	 * Gets the width of the renderer
	 * 
	 * @return the renderer's width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Gets the height of the renderer
	 * 
	 * @return the renderer's height
	 */
	public int getHeight()
	{
		return height;
	}
	
	public void render()
	{
		getShader().bind();
		getTexture().bind();
		
		getShader().setUniform("color", color);
		
		Vector2i halfSize = new Vector2i(width / 2, height / 2);
		
		getShader().setUniform("trans_Position", Util.pixelCoordToWindow(getTransform().getGlobalPosition().add(halfSize)));
		getShader().setUniform("trans_Rotation", getTransform().getGlobalRotation());
		getShader().setUniform("trans_Scale", Util.pixelDimensionToWindow(halfSize));
		
		if (getUniformConfig() != null)
		{
			getUniformConfig().setUniforms(getShader());
		}
		
		getApplication().getRenderingEngine().updateOverlayBrightness(getShader(), getAllowLighting());
		
		squareMesh.render();
	}
	
	private void resize()
	{
		FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
		width = fontMetrics.stringWidth(text);
		height = fontMetrics.getHeight();
	}
	
	private void genTexture()
	{
		resize();
		
		BufferedImage drawBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		drawBuffer.getGraphics().setFont(font);
		drawBuffer.getGraphics().drawString(text, 0, 0);
		
		setTexture(new Texture(drawBuffer));
	}
}
