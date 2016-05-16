package engine.math;

/**
 * 3x3 transformation matrix for performing
 * 3D transformations
 * 
 * @author Lenny Litvak
 *
 */
public class Matrix3x3
{
	private float[][] matrix;
	
	public static Matrix3x3 identity()
	{
		Matrix3x3 out = new Matrix3x3();
		
		out.set(0, 0, 1);
		out.set(1, 1, 1);
		out.set(2, 2, 1);
		
		return out;
	}
	
	public static Matrix3x3 fromPosition(float x, float y)
	{
		Matrix3x3 out = identity();
		
		out.set(0, 2, x);
		out.set(1, 2, y);
		
		return out;
	}
	
	public static Matrix3x3 fromPosition(Vector2 pos)
	{
		return fromPosition(pos.getX(), pos.getY());
	}
	
	public static Matrix3x3 fromAngle(float angle)
	{
		Matrix3x3 out = new Matrix3x3();
		
		float cosA = Mathf.cos(angle);
		float sinA = Mathf.sin(angle);
		
		out.set(0,  0,  cosA);
		out.set(0, 1, sinA);
		out.set(1, 0, sinA);
		out.set(1, 1, cosA);
		
		out.set(2, 2, 1);
		
		return out;
	}
	
	public static Matrix3x3 fromScale(float x, float y)
	{
		Matrix3x3 out = new Matrix3x3();
		
		out.set(0, 0, x);
		out.set(1, 1, y);
		out.set(2, 2, 1);
		
		return out;
	}
	
	public static Matrix3x3 fromScale(Vector2 scale)
	{
		return fromScale(scale.getX(), scale.getY());
	}
	
	public Matrix3x3()
	{
		matrix = new float[3][3];
	}
	
	public Matrix3x3 mul(Matrix3x3 m3)
	{
		Matrix3x3 out = new Matrix3x3();
		
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 3; x++)
			{
				out.set(y, x, matrix[y][0] * m3.get(0, x)
							+ matrix[y][1] * m3.get(1, x)
							+ matrix[y][2] * m3.get(2, x));
			}
		}
		
		return out;
	}
	
	public Vector2 mul(Vector2 v2)
	{
		float nx = matrix[0][0] * v2.getX() + matrix[0][1] * v2.getY();
		float ny = matrix[1][0] * v2.getX() + matrix[1][1] * v2.getY();
		
		return new Vector2(nx, ny);
	}
	
	public void set(int y, int x, float value)
	{
		matrix[y][x] = value;
	}
	
	public float get(int y, int x)
	{
		return matrix[y][x];
	}
	
	public float[][] getMatrix()
	{
		return matrix;
	}
}
