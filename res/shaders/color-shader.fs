#version 120

uniform vec4 color;

#include "dark-overlay.fsh"

void main()
{
	gl_FragColor = adjustOverlay(color);
}