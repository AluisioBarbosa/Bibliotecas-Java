package aluisio.compgrafica.tridimensional;

public class Camera {
	private Vector3D u, v, n;
	private Vector3D eye;
	private Vector3D look;
	private Vector3D up;
	
	private float anguloAbertura, near, far, aspect;

	public Camera(Vector3D eye, Vector3D look, Vector3D up, float anguloAbertura, float near, float far, float aspect) {
		this.eye = eye;
		this.look = look;
		this.up = up;
		this.anguloAbertura = anguloAbertura;
		this.near = near;
		this.far = far;
		this.aspect = aspect;
		
		calcularUVN();
	}
	
	
	public void calcularUVN() {
		this.n = Vector3D.sub(eye, look);
		this.n.normalize();
		
		float[] UPxN = Vector3D.cross(up, n);
		this.u = new Vector3D(UPxN[0], UPxN[1], UPxN[2]);
		this.u.normalize();
		
		float[] NxU = Vector3D.cross(n, u);
		this.v = new Vector3D(NxU[0], NxU[1], NxU[2]);
		this.v.normalize();
		
	}
	
	
	
	public void roll(float angulo) {
		
	}
	
	public void pitch(float angulo) {
		
	}
	
	public void yaw(float angulo) {
		
	}
	
	
}
