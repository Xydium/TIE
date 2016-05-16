#version 140

in vec4 in_Vertex;
in vec2 in_TexCoord;

out vec2 texCoord;

void main()
{
	texCoord = vec2(1.0 - in_TexCoord.y, in_TexCoord.x);
	
	gl_Position = in_Vertex;
}