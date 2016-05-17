#version 120
#include "dark-overlay.fsh"

uniform sampler2D texSampler;

varying vec2 ex_TexCoord;

void main()
{
	gl_FragColor = adjustOverlay(texture2D(texSampler, ex_TexCoord));
}