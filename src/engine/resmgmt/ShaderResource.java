package engine.resmgmt;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.glCreateProgram;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores references to GL shaders and the number of times
 * they have been referenced
 * 
 * @author Lenny Litvak
 *
 */
public class ShaderResource extends Resource
{
	private int program;
	
	private HashMap<String, Integer> uniforms;
	private ArrayList<String> uniformNames;
	private ArrayList<String> uniformTypes;
	
	/**
	 * Creates a new shader resource object which
	 * allocates a new GL shader program
	 */
	public ShaderResource()
	{
		program = glCreateProgram();
		
		if (program == 0)
		{
			System.err.println("Error: Shader creation failed: could not find valid memory location in constructor");
			System.exit(1);
		}
		
		uniforms = new HashMap<String, Integer>();
		uniformNames = new ArrayList<String>();
		uniformTypes = new ArrayList<String>();
	}
	
	@Override
	protected void finalize()
	{
		glDeleteBuffers(program);
	}
	
	/**
	 * Gets the pointer to which the program is allocated
	 * 
	 * @return the GL shader program
	 */
	public int getProgram()
	{
		return program;
	}
	
	/**
	 * Gets a list of the uniforms for the shader
	 * 
	 * @return the uniforms
	 */
	public HashMap<String, Integer> getUniforms()
	{
		return uniforms;
	}
	
	/**
	 * Gets a list of the uniform names for the shader
	 * 
	 * @return the uniform names
	 */
	public ArrayList<String> getUniformNames()
	{
		return uniformNames;
	}
	
	/**
	 * Gets a list of the uniform types for the shader
	 * 
	 * @return the uniform types
	 */
	public ArrayList<String> getUniformTypes()
	{
		return uniformTypes;
	}
}
