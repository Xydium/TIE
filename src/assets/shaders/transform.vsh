
uniform vec2 trans_Position;
uniform float trans_Rotation;
uniform vec2 trans_Scale;

void rotate(inout vec2 v2, in float angle)
{
	float sinA = sin(-angle);
	float cosA = cos(angle);
	
	v2 = vec2(v2.x * cosA - v2.y * sinA, v2.x * sinA + v2.y * cosA);
}

vec4 transform(vec4 vert)
{
	vec2 pos = vert.xy;
	rotate(pos, trans_Rotation);
	
	return vec4(trans_Position + pos * trans_Scale, vert.zw);
}