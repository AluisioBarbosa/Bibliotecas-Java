package aluisio.compgrafica.tridimensional;

public class Vector3D {
	private float x;
	private float y;
	private float z;
	
	public Vector3D(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Vector3D v2) {
		this.x += v2.x;
		this.y += v2.y;
		this.z += v2.z;
	}
	
	public static Vector3D add(Vector3D v1, Vector3D v2) {
		Vector3D result = new Vector3D (v1.x + v2.x, 
										v1.y + v2.y, 
										v1.z + v2.z);
		
		return result;
	}
	
	public void sub(Vector3D v2) {
		this.x -= v2.x;
		this.y -= v2.y;
		this.z -= v2.z;
	}
	
	public static Vector3D sub(Vector3D v1, Vector3D v2) {
		Vector3D result = new Vector3D(v1.x - v2.x,
									  v1.y - v2.y,
									  v1.z - v2.z);
		
		return result;
	}
	
	public static float dot(Vector3D v1, Vector3D v2) {
		return (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z);
	}
	
	public static float[] cross(Vector3D v1, Vector3D v2) {
		float[] resultado = new float[3];
		
		resultado[0] = v1.y * v2.z - v1.z * v2.y;
	    resultado[1] = v1.z * v2.x - v1.x * v2.z;
	    resultado[2] = v1.x * v2.y - v1.y * v2.x;
		
		return resultado;
	}
	
	public float length() {
		float norma = 0;
		norma = (float) (Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
		norma = (float) Math.sqrt(norma);
		return norma;
	}
	
	public void normalize() {
		float norma = this.length();
		this.x = this.x / norma;
		this.y = this.y / norma;
		this.z = this.z / norma;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	
	
	
}
