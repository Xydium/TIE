#version 140

uniform sampler2D texture;

in vec2 texCoord;

#include "dark-overlay.fsh"

out vec4 out_FragColor;

void main()
{
	vec2 tc = vec2(1.0 - texCoord.y, texCoord.x);
	out_FragColor = adjustOverlay(texture(texture, tc));
}