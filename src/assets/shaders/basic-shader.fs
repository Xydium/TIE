#version 120

uniform sampler2D texture;

varying vec2 texCoord;

#include "dark-overlay.fsh"

void main()
{
	vec2 tc = vec2(1.0 - texCoord.y, texCoord.x);
	gl_FragColor = adjustOverlay(texture2D(texture, tc));
}