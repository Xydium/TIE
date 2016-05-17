#version 120
#include "transform.vsh"

varying vec2 ex_TexCoord;

void main()
{
	ex_TexCoord = vec2(1.0 - gl_MultiTexCoord0.y, gl_MultiTexCoord0.x);

	gl_Position = transform(gl_Vertex);
}