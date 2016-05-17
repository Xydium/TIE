#version 150

precision highp float;

uniform sampler2D texSampler;

uniform float time;
uniform float frequency;
uniform float amplitude;

out vec4 out_Color;

in vec2 ex_TexCoord;

void main()
{
	vec2 aux = ex_TexCoord;
	aux.x = clamp(sin(30.0 * aux.y + frequency * time) * amplitude + aux.x, 0.0, 1.0);
	aux.y = clamp(sin(30.0 * aux.x + frequency * time) * amplitude + aux.y, 0.0, 1.0);

	out_Color = texture(texSampler, aux);
}