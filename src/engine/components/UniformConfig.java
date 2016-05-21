package engine.components;

import engine.rendering.Shader;

/**
 * Simple interface that can be used to set the uniforms of the
 * a shader
 * 
 * @author Lenny Litvak
 * @author Tim Hornick
 *
 */
public interface UniformConfig
{
	public void setUniforms(Shader s);
}
