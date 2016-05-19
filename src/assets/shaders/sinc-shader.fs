#version 120

uniform vec4 color;
uniform int deg;

void main()
{
	gl_FragColor = vec4(color.xyz * sin(radians(deg)), color.w);
}