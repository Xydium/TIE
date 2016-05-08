package engine.rendering;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import engine.math.Vector2;
import engine.resmgmt.ShaderResource;

/**
 * GL Shader program comprised of a vertex and fragment shader
 * 
 * @author Lenny Litvak
 *
 */
public class Shader
{
	private static HashMap<String, ShaderResource> loadedShaders = new HashMap<String, ShaderResource>();
	
	private ShaderResource resource;
	private String fileName;
	
	/**
	 * Creates a new shader object from the given base file name
	 * which searches for two shader files:
	 * fileName.vs (vertex shader)
	 * fileName.fs (fragment shader)
	 * 
	 * @param fileName the base file name of the shader
	 */
	public Shader(String fileName)
	{
		this.fileName = fileName;
		ShaderResource oldResource = loadedShaders.get(fileName);
		
		if (oldResource != null)
		{
			resource = oldResource;
			resource.addReference();
		}
		else
		{
			resource = new ShaderResource();
			
			String vertexShaderText = loadShader(fileName + ".vs");
			String fragmentShaderText = loadShader(fileName + ".fs");
			
			addVertexShader(vertexShaderText);
			addFragmentShader(fragmentShaderText);
			
			addAllAttributes(vertexShaderText);
			
			compileShader();
			
			addAllUniforms(vertexShaderText);
			addAllUniforms(fragmentShaderText);
			
			loadedShaders.put(fileName, resource);
		}
	}
	
	@Override
	protected void finalize()
	{
		if (resource.removeReference() && !fileName.isEmpty())
		{
			loadedShaders.remove(fileName);
		}
	}
	
	/**
	 * Binds the shader to be the current shader
	 */
	public void bind()
	{
		glUseProgram(resource.getProgram());
	}
	
	/**
	 * Sets a uniform variable of the given name in this shader
	 * to be a given integer value
	 * 
	 * @param uniformName the name of the uniform variable
	 * @param value the new value of the uniform variable
	 */
	public void setUniform(String uniformName, int value)
	{
		glUniform1i(resource.getUniforms().get(uniformName), value);
	}
	
	/**
	 * Sets a uniform variable of the given name in this shader
	 * to be a given float value
	 * 
	 * @param uniformName the name of the uniform variable
	 * @param value the new value of the uniform variable
	 */
	public void setUniform(String uniformName, float value)
	{
		glUniform1f(resource.getUniforms().get(uniformName), value);
	}
	
	/**
	 * Sets a uniform variable of the given name in this shader
	 * to be a given Vector2 value
	 * 
	 * @param uniformName the name of the uniform variable
	 * @param value the new value of the uniform variable
	 */
	public void setUniform(String uniformName, Vector2 value)
	{
		glUniform2f(resource.getUniforms().get(uniformName), value.getX(), value.getY());
	}
	
	/**
	 * Sets a uniform variable of the given name in this shader
	 * to be a given Color value
	 * 
	 * @param uniformName the name of the uniform variable
	 * @param value the new value of the uniform variable
	 */
	public void setUniform(String uniformName, Color value)
	{
		glUniform3f(resource.getUniforms().get(uniformName), value.getR(), value.getG(), value.getB());
	}
	
	private void addAllAttributes(String source)
	{
		final String ATTRIBUTE_KEYWORD = "attribute";
		int attributeStartLocation = source.indexOf(ATTRIBUTE_KEYWORD);
		int attribNumber = 0;
		
		while (attributeStartLocation != -1)
		{
			if (!(attributeStartLocation != 0 && (Character.isWhitespace(source.charAt(attributeStartLocation - 1))
				|| source.charAt(attributeStartLocation - 1) == ';')
				&& Character.isWhitespace(source.charAt(attributeStartLocation + ATTRIBUTE_KEYWORD.length()))))
			{
				attributeStartLocation = source.indexOf(ATTRIBUTE_KEYWORD, attributeStartLocation + ATTRIBUTE_KEYWORD.length());
				continue;
			}
			
			int begin = attributeStartLocation + ATTRIBUTE_KEYWORD.length() + 1;
			int end = source.indexOf(";", begin);

			String attributeLine = source.substring(begin, end).trim();
			String attributeName = attributeLine.substring(attributeLine.indexOf(' ') + 1, attributeLine.length()).trim();

			setAttribLocation(attributeName, attribNumber);
			attribNumber++;

			attributeStartLocation = source.indexOf(ATTRIBUTE_KEYWORD, attributeStartLocation + ATTRIBUTE_KEYWORD.length());
		}
	}
	
	private class GLSLStruct
	{
		public String name;
		public String type;
	}
	
	private HashMap<String, ArrayList<GLSLStruct>> findUniformStructs(String source)
	{
		HashMap<String, ArrayList<GLSLStruct>> result = new HashMap<String, ArrayList<GLSLStruct>>();
		
		final String STRUCT_KEYWORD = "struct";
		int structStartLocation = source.indexOf(STRUCT_KEYWORD);
		
		while (structStartLocation != -1)
		{
			if (!(structStartLocation != 0 && (Character.isWhitespace(source.charAt(structStartLocation - 1))
				|| source.charAt(structStartLocation - 1) == ';')
				&& Character.isWhitespace(source.charAt(structStartLocation + STRUCT_KEYWORD.length()))))
			{
				structStartLocation = source.indexOf(STRUCT_KEYWORD, structStartLocation + STRUCT_KEYWORD.length());
				continue;
			}
			
			int nameBegin = structStartLocation + STRUCT_KEYWORD.length() + 1;
			int braceBegin = source.indexOf("{", nameBegin);
			int braceEnd = source.indexOf("}", braceBegin);

			String structName = source.substring(nameBegin, braceBegin).trim();
			ArrayList<GLSLStruct> glslStructs = new ArrayList<GLSLStruct>();

			int componentSemicolonPos = source.indexOf(";", braceBegin);
			
			while (componentSemicolonPos != -1 && componentSemicolonPos < braceEnd)
			{
				int componentNameEnd = componentSemicolonPos + 1;
				
				while (Character.isWhitespace(source.charAt(componentNameEnd - 1)) || source.charAt(componentNameEnd - 1) == ';')
				{
					componentNameEnd--;
				}
				
				int componentNameStart = componentSemicolonPos;
				
				while (!Character.isWhitespace(source.charAt(componentNameStart - 1)))
				{
					componentNameStart--;
				}

				int componentTypeEnd = componentNameStart;

				while (Character.isWhitespace(source.charAt(componentTypeEnd - 1)))
				{
					componentTypeEnd--;
				}

				int componentTypeStart = componentTypeEnd;

				while (!Character.isWhitespace(source.charAt(componentTypeStart - 1)))
				{
					componentTypeStart--;
				}
				
				String componentName = source.substring(componentNameStart, componentNameEnd);
				String componentType = source.substring(componentTypeStart, componentTypeEnd);

				GLSLStruct glslStruct = new GLSLStruct();
				glslStruct.name = componentName;
				glslStruct.type = componentType;

				glslStructs.add(glslStruct);

				componentSemicolonPos = source.indexOf(";", componentSemicolonPos + 1);
			}
			
			result.put(structName, glslStructs);

			structStartLocation = source.indexOf(STRUCT_KEYWORD, structStartLocation + STRUCT_KEYWORD.length());
		}
		
		return result;
	}
	
	private void addAllUniforms(String source)
	{
		HashMap<String, ArrayList<GLSLStruct>> structs = findUniformStructs(source);
		
		final String UNIFORM_KEYWORD = "uniform";
		int uniformStartLocation = source.indexOf(UNIFORM_KEYWORD);
		
		while (uniformStartLocation != -1)
		{
			if (!(uniformStartLocation != 0 && (Character.isWhitespace(source.charAt(uniformStartLocation - 1))
				|| source.charAt(uniformStartLocation - 1) == ';')
				&& Character.isWhitespace(source.charAt(uniformStartLocation + UNIFORM_KEYWORD.length()))))
			{
				uniformStartLocation = source.indexOf(UNIFORM_KEYWORD, uniformStartLocation + UNIFORM_KEYWORD.length());
				continue;
			}
			
			int begin = uniformStartLocation + UNIFORM_KEYWORD.length() + 1;
			int end = source.indexOf(";", begin);

			String uniformLine = source.substring(begin, end).trim();

			int whiteSpacePos = uniformLine.indexOf(' ');
			String uniformName = uniformLine.substring(whiteSpacePos + 1, uniformLine.length()).trim();
			String uniformType = uniformLine.substring(0, whiteSpacePos).trim();
			
			resource.getUniformNames().add(uniformName);
			resource.getUniformTypes().add(uniformType);
			
			addUniform(uniformName, uniformType, structs);

			uniformStartLocation = source.indexOf(UNIFORM_KEYWORD, uniformStartLocation + UNIFORM_KEYWORD.length());
		}
	}
	
	private void addUniform(String name, String type, HashMap<String, ArrayList<GLSLStruct>> structs)
	{
		boolean addThis = true;
		ArrayList<GLSLStruct> structComponents = structs.get(type);

		if (structComponents != null)
		{
			addThis = false;
			for (GLSLStruct struct : structComponents)
			{
				addUniform(name + "." + struct.name, struct.type, structs);
			}
		}

		if (!addThis)
		{
			return;
		}

		int uniformLocation = glGetUniformLocation(resource.getProgram(), name);

		if (uniformLocation == 0xFFFFFFFF)
		{
			System.err.println("Error: Could not find uniform: " + name);
			new Exception().printStackTrace();
			System.exit(1);
		}

		resource.getUniforms().put(name, uniformLocation);
	}
	
	private void addVertexShader(String source)
	{
		addProgram(source, GL_VERTEX_SHADER);
	}
	
	private void addFragmentShader(String source)
	{
		addProgram(source, GL_FRAGMENT_SHADER);
	}
	
	private void setAttribLocation(String name, int location)
	{
		glBindAttribLocation(resource.getProgram(), location, name);
	}
	
	private void compileShader()
	{
		glLinkProgram(resource.getProgram());
		
		if (glGetProgrami(resource.getProgram(), GL_LINK_STATUS) == 0)
		{
			System.err.println(glGetProgramInfoLog(resource.getProgram(), 1024));
			System.exit(1);
		}
		
		glValidateProgram(resource.getProgram());
		
		if(glGetProgrami(resource.getProgram(), GL_VALIDATE_STATUS) == 0)
		{
			System.err.println(glGetProgramInfoLog(resource.getProgram(), 1024));
			System.exit(1);
		}
	}
	
	private static String loadShader(String fileName)
	{
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		final String INCLUDE_DIRECTIVE = "#include";
		
		try
		{
			shaderReader = new BufferedReader(new FileReader("res/shaders/" + fileName));
			String line;
			
			while ((line = shaderReader.readLine()) != null)
			{
				if (line.startsWith(INCLUDE_DIRECTIVE))
				{
					shaderSource.append(loadShader(line.substring(INCLUDE_DIRECTIVE.length() + 2, line.length() - 1)));
				}
				else
				{
					shaderSource.append(line).append("\n");
				}
			}
			
			shaderReader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return shaderSource.toString();
	}
	
	private void addProgram(String source, int type)
	{
		int shader = glCreateShader(type);
		
		if (shader == 0)
		{
			System.err.println("ERROR: Shader creation failed; could not find valid memory location when adding shader");
			System.exit(1);
		}
		
		glShaderSource(shader, source);
		glCompileShader(shader);
		
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(resource.getProgram(), shader);
	}
}
