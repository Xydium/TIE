
uniform vec2 windowSize;
uniform float lightPercent;

vec4 adjustOverlay(vec4 baseColor)
{
	vec2 regCoords =  gl_FragCoord.xy / windowSize; 
	vec2 windowPosAdj = (regCoords * 2) - 1;
	
	float maxDist = (windowSize.x / windowSize.y) * lightPercent;
	float ratio = 1.0 - (length(windowPosAdj) / maxDist);
	
	if (lightPercent != -1.0)
	{
		return vec4(baseColor.rgb * lightPercent * ratio, baseColor.a);
	}
	else
	{
		return baseColor;
	}
}