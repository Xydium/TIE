#version 150

precision highp float;

out vec4 out_Color;

in vec4 ex_Color;

void main()
{
	out_Color = ex_Color;
}