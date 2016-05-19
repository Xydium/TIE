#version 120
#include "dark-overlay.fsh"

uniform vec4 color;
uniform float angle;

void main()
{
	gl_FragColor = adjustOverlay(vec4(color.rgb * sin(angle), color.a));
}