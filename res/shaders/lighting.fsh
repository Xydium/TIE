uniform bool lighting;
uniform vec2 lightPos;
uniform vec2 windowSize;
uniform float range;
uniform float brightness;

float limit(float n)
{
	if (n < 0.0)
		return 0.0;
	
	if (n > 1.0)
		return 1.0;
	
	return n;
}

vec4 calcLightingColor(vec4 baseColor)
{
	vec2 regCoords =  gl_FragCoord.xy / windowSize; 
	vec2 centCoords = (regCoords * 2) - 1;
	centCoords = centCoords * (windowSize / windowSize.xx);

	vec2 distV2 = lightPos - centCoords;
	float dist = sqrt(distV2.x * distV2.x + distV2.y * distV2.y);

	float ratio;
	
	if (range <= 0.01)
	{
		ratio = 0;
	}
	else
	{
		ratio = (1 - limit(dist / range)) * brightness;
	}
	
	if (lighting)
	{
	}
	else
	{
		if (dist > range)
			return baseColor;
		else
			return vec4(0, 0, 0, 0);
	}
}
