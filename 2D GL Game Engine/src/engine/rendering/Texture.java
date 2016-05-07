package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import engine.core.Util;
import engine.resmgmt.TextureResource;

/**
 * Class that stores a texture which is loaded from a file
 * 
 * @author Lenny Litvak
 *
 */
public class Texture
{
	private static HashMap<String, TextureResource> loadedTextures = new HashMap<String, TextureResource>();
	
	private TextureResource resource;
	private String fileName;
	
	/**
	 * Creates a new texture from a given file
	 * 
	 * @param fileName the file to load the texture from
	 */
	public Texture(String fileName)
	{
		this.fileName = fileName;
		TextureResource oldResource = loadedTextures.get(fileName);
		
		if (oldResource != null)
		{
			resource = oldResource;
			resource.addReference();
		}
		else
		{
			resource = loadTexture(fileName);
			loadedTextures.put(fileName, resource);
		}
	}
	
	@Override
	protected void finalize()
	{
		if (resource.removeReference() && !fileName.isEmpty())
		{
			loadedTextures.remove(fileName);
		}
	}
	
	/**
	 * Binds the texture to GL
	 */
	public void bind()
	{
		bind(0);
	}
	
	/**
	 * Binds the texture to the given sampler slot
	 * 
	 * @param samplerSlot the slot to bind to
	 */
	public void bind(int samplerSlot)
	{
		assert(samplerSlot >= 0 && samplerSlot <= 31);
		
		glActiveTexture(GL_TEXTURE0 + samplerSlot);
		glBindTexture(GL_TEXTURE_2D, getID());
	}
	
	/**
	 * Gets the ID of the texture resource in GL
	 * 
	 * @return the texture ID
	 */
	public int getID()
	{
		return resource.getID();
	}
	
	private static TextureResource loadTexture(String fileName)
	{
		try
		{
			BufferedImage image = ImageIO.read(new File("res/textures/" + fileName));
			int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
			
			ByteBuffer buffer = Util.createByteBuffer(image.getHeight() * image.getWidth() * 4);
			boolean hasAlpha = image.getColorModel().hasAlpha();
			
			for (int x = 0; x < image.getWidth(); x++)
			{
				for (int y = 0; y < image.getHeight(); y++)
				{
					int pixel = pixels[y * image.getWidth() + x];
					
					buffer.put((byte)((pixel >> 16) & 0xFF));
					buffer.put((byte)((pixel >> 8) & 0xFF));
					buffer.put((byte)((pixel) & 0xFF));
					
					if (hasAlpha)
					{
						buffer.put((byte)((pixel >> 24) & 0xFF));
					}
					else
					{
						buffer.put((byte)(0xFF));
					}
				}
			}
			
			buffer.flip();
			
			TextureResource resource = new TextureResource();
			glBindTexture(GL_TEXTURE_2D, resource.getID());
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			
			return resource;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
