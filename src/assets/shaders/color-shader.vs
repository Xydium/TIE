#version 120

in vec4 in_Vertex;

void main()
{
	gl_Position = in_Vertex.xy;
}