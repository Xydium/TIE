#version 300

uniform vec4 color;

#include "dark-overlay.fsh"

out vec4 out_FragColor;

void main()
{
	out_FragColor = adjustOverlay(color);
}