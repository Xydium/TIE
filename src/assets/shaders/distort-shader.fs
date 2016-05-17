#version 120
#include "dark-overlay.fsh"

uniform sampler2D texSampler;

uniform float time;
uniform float frequency;
uniform float amplitude;

varying vec2 ex_TexCoord;

void main()
{
	vec2 aux = ex_TexCoord;
	aux.x = clamp(sin(30.0 * aux.y + frequency * time) * amplitude + aux.x, 0.0, 1.0);
	aux.y = clamp(sin(30.0 * aux.x + frequency * time) * amplitude + aux.y, 0.0, 1.0);

	gl_FragColor = adjustOverlay(texture2D(texSampler, aux));
}