#version 120

varying vec2 texCoord;

void main()
{
	texCoord = vec2(1.0 - gl_MultiTexCoord0.y, gl_MultiTexCoord0.x);
	
	gl_Position = gl_Vertex;
	//gl_FrontColor = gl_Color;
}