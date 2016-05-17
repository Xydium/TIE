#version 120
#include "dark-overlay.fsh"

uniform vec4 color;

void main()
{
	gl_FragColor = adjustOverlay(color);
}