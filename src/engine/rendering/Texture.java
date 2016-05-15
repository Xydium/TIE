package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import engine.resmgmt.TextureResource;
import engine.utility.Log;
import engine.utility.Util;

/**
 * Class that stores a texture which is loaded from a file
 *
 * @author Lenny Litvak
 * @author Chris Jerrett
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
			resource = bufferedImageToTexture(loadBufferedImage(fileName));
			loadedTextures.put(fileName, resource);
		}
	}

	/**
	 * Creates a new texture from a given buffered image
	 * 
	 * @param bufferedImage the image to read from
	 */
	public Texture(BufferedImage bufferedImage)
	{
		this.fileName = "";
		this.resource = bufferedImageToTexture(bufferedImage);
	}
	
	/**
	 * Creates a new texture from the given filename, if the filename
	 * already exists, it loads from the existing texture resource
	 * 
	 * @param bufferedImage the buffered image to possibly write to the filename
	 * @param fileName the file name for the image
	 */
	public Texture(BufferedImage bufferedImage, String fileName)
	{
		TextureResource oldResource = loadedTextures.get(fileName);
		
		if (oldResource != null)
		{
			resource = oldResource;
			resource.addReference();
		}
		else
		{
			resource = bufferedImageToTexture(bufferedImage);
			loadedTextures.put(fileName, resource);
		}
	}

	/**
	 * Cleans up the texture after no more references exist
	 * to it
	 */
	@Override
	protected void finalize()
	{
		if (resource.removeReference() && !fileName.isEmpty())
		{
			loadedTextures.remove(fileName);
		}
	}

	/**
	 * Binds the texture to GL at the first texture slot
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

	/**
	 * Utility method for loading a given BufferedImage to a byte buffer
	 * 
	 * @param image the image to convert
	 * @return the ByteBuffer containing the image
	 */
	public static ByteBuffer loadImageToByteBuffer(BufferedImage image)
	{
		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

		ByteBuffer buffer = Util.createByteBuffer(image.getHeight() * image.getWidth() * 4);
		boolean hasAlpha = image.getColorModel().hasAlpha();

		for (int y = 0; y < image.getHeight(); y++)
		{
			for (int x = 0; x < image.getWidth(); x++)
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
		
		return buffer;
	}
	
	/**
	 * Loads a buffered image from the given file name
	 * 
	 * @param fileName the file name of the image
	 * @return the image at the given file name
	 */
	public static BufferedImage loadBufferedImage(String fileName)
	{
		try
		{
			return ImageIO.read(Texture.class.getResourceAsStream("/assets/textures/" + fileName));
		}
		catch (IOException e)
		{
			Log.error("Error loading texture: " + fileName);
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}

	private static TextureResource bufferedImageToTexture(BufferedImage image)
	{
		ByteBuffer buffer = loadImageToByteBuffer(image);

		TextureResource resource = new TextureResource();
		glBindTexture(GL_TEXTURE_2D, resource.getID());

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		return resource;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Texture [resource=" + resource + ", fileName=" + fileName + "]";
	}
}
