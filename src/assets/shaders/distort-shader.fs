#version 140

uniform sampler2D currentTexture;

uniform float time;
uniform float frequency;
uniform float amplitude;

in vec2 texCoord;

out vec4 out_FragColor;

#include "dark-overlay.fsh"

float clamp(float a, float min, float max)
{
	if (a < min)
		return min;
	
	if (a > max)
		return max;
	
	return a;
}

void main()
{
	vec2 aux = texCoord;
	aux.x = clamp(sin(30.0 * aux.y + frequency * time) * amplitude + aux.x, 0.0, 1.0);
	aux.y = clamp(sin(30.0 * aux.x + frequency * time) * amplitude + aux.y, 0.0, 1.0);
	
	out_FragColor = adjustOverlay(texture(currentTexture, aux));
}