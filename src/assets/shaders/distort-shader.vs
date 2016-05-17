#version 150

uniform vec2 transPosition;
uniform float transRotation;
uniform vec2 transScale;

in vec2 in_Position;
in vec2 in_TexCoord;

out vec2 ex_TexCoord;

void rotate(inout vec2 v2, in float angle)
{
	float sinA = sin(-angle);
	float cosA = cos(angle);
	
	v2 = vec2(v2.x * cosA - v2.y * sinA, v2.x * sinA + v2.y * cosA);
}

void main()
{
	ex_TexCoord = in_TexCoord;
	
	vec2 pos = in_Position * transScale;
	rotate(pos, transRotation);

	gl_Position = vec4(transPosition + pos, 1.0, 1.0);
}