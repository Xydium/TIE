#version 120
#include "transform.vsh"

void main()
{
	gl_Position = transform(gl_Vertex);
}