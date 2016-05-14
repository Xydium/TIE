#version 120

uniform vec4 color;

#include "lighting.fsh"

void main()
{
	gl_FragColor = calcLightingColor(color);
}