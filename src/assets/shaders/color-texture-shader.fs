#version 120
#include "dark-overlay.fsh"

uniform sampler2D texSampler;

uniform vec4 color;

varying vec2 ex_TexCoord;

void main()
{
	vec4 tCol = texture2D(texSampler, ex_TexCoord);
	gl_FragColor = adjustOverlay(vec4(color.rgb, tCol.a));
}