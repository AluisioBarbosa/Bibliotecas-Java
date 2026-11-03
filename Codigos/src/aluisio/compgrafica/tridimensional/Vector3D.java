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
	
	public void sub(Vector3D v2) {
		this.x -= v2.x;
		this.y -= v2.y;
		this.z -= v2.z;
	}
	
	public float dot(Vector3D v2) {
		return (this.x * v2.x) + (this.y * v2.y) + (this.z * v2.z);
	}
	
	public float[] cross(Vector3D v2) {
		float[] resultado = new float[3];
		
		resultado[0] = this.y * v2.z - this.z * v2.y;
	    resultado[1] = this.z * v2.x - this.x * v2.z;
	    resultado[2] = this.x * v2.y - this.y * v2.x;
		
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
