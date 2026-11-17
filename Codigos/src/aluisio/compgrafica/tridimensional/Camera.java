package aluisio.compgrafica.tridimensional;

import com.jogamp.opengl.GL2;

public class Camera {
	private Vector3D u, v, n;
	private Vector3D eye;
	private Vector3D look;
	private Vector3D up;
	
	public Camera(Vector3D eye, Vector3D look, Vector3D up) {
		this.eye = eye;
		this.look = look;
		this.up = up;
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
		float anguloRadiano = (float) Math.toRadians(angulo);
		float cos = (float) Math.cos(anguloRadiano);
		float seno = (float) Math.sin(anguloRadiano);
		
		float ux = u.getX() * cos - v.getX() * seno;
		float uy = u.getY() * cos - v.getY() * seno;
		float uz = u.getZ() * cos - v.getZ() * seno;
		
		float vx = u.getX() * seno + v.getX() * cos;
		float vy = u.getY() * seno + v.getY() * cos;
		float vz = u.getZ() * seno + v.getZ() * cos;
		
		this.u.setX(ux); 
		this.u.setY(uy); 
		this.u.setZ(uz);
		
		this.v.setX(vx); 
		this.v.setY(vy); 
		this.v.setZ(vz);
	}
	
	public void pitch(float angulo) {
		float anguloRadiano = (float) Math.toRadians(angulo);
		float cos = (float) Math.cos(anguloRadiano);
		float seno = (float) Math.sin(anguloRadiano);
		
		float vx = v.getX() * cos - n.getX() * seno;
		float vy = v.getY() * cos - n.getY() * seno;
		float vz = v.getZ() * cos - n.getZ() * seno;
		
		float nx = v.getX() * seno + n.getX() * cos;
		float ny = v.getY() * seno + n.getY() * cos;
		float nz = v.getZ() * seno + n.getZ() * cos;
		
		this.v.setX(vx); 
		this.v.setY(vy); 
		this.v.setZ(vz);
		
		this.n.setX(nx); 
		this.n.setY(ny); 
		this.n.setZ(nz);
	}
	
	public void yaw(float angulo) {
		float anguloRadiano = (float) Math.toRadians(angulo);
		float cos = (float) Math.cos(anguloRadiano);
		float seno = (float) Math.sin(anguloRadiano);
		
		float ux = u.getX() * cos + n.getX() * seno;
		float uy = u.getY() * cos + n.getY() * seno;
		float uz = u.getZ() * cos + n.getZ() * seno;
		
		float nx = -u.getX() * seno + n.getX() * cos;
		float ny = -u.getY() * seno + n.getY() * cos;
		float nz = -u.getZ() * seno + n.getZ() * cos;
		
		this.u.setX(ux); 
		this.u.setY(uy); 
		this.u.setZ(uz);
		
		this.n.setX(nx); 
		this.n.setY(ny); 
		this.n.setZ(nz);
	}
	
	public void slide(float du, float dv, float dn) {
		float novoX = eye.getX() + (du * u.getX()) + (dv * v.getX()) + (dn * n.getX());
		float novoY = eye.getY() + (du * u.getY()) + (dv * v.getY()) + (dn * n.getY());
		float novoZ = eye.getZ() + (du * u.getZ()) + (dv * v.getZ()) + (dn * n.getZ());
		
		this.eye.setX(novoX);
		this.eye.setY(novoY);
		this.eye.setZ(novoZ);
	}
	
	
	public void setModeloVisualizacao(GL2 gl) {
		float[] m = new float[16];

		m[0] = u.getX(); m[4] = u.getY(); m[8] = u.getZ();  m[12] = -Vector3D.dot(eye, u);
		m[1] = v.getX(); m[5] = v.getY(); m[9] = v.getZ();  m[13] = -Vector3D.dot(eye, v);
		m[2] = n.getX(); m[6] = n.getY(); m[10] = n.getZ(); m[14] = -Vector3D.dot(eye, n);
		m[3] = 0.0f;     m[7] = 0.0f;     m[11] = 0.0f;     m[15] = 1.0f;

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadMatrixf(m, 0);
	}
}
