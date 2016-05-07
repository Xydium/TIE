uniform sampler2D texture;

varying vec2 vTexCoord;

void main()
{
	vec4 col = texture2D(texture, vTexCoord);
	float avg = (col.x + col.y + col.z) / 3.0;
	
	gl_FragColor = vec4(avg, avg, avg, col.w);
	//gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
}