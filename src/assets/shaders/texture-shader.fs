#version 150

precision highp float;

uniform sampler2D texSampler;

out vec4 out_Color;

in vec2 ex_TexCoord;

void main()
{
	out_Color = texture(texSampler, ex_TexCoord);
}