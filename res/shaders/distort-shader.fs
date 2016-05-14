#version 120

uniform sampler2D currentTexture;

uniform float time;
uniform float frequency;
uniform float amplitude;

varying vec2 texCoord;

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
	
	gl_FragColor = texture2D(currentTexture, aux);
}