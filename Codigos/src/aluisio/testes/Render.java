package aluisio.testes;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import aluisio.codigos.KeyboardManager;
import aluisio.compgrafica.tridimensional.Face;
import aluisio.compgrafica.tridimensional.Mesh;
import aluisio.compgrafica.tridimensional.Ponto3D;
import aluisio.compgrafica.tridimensional.Transformacoes;
import aluisio.compgrafica.tridimensional.Vector3D;

public class Render implements GLEventListener {
	
	private GL2 gl;
	private int height;
	private int width;
	private KeyboardManager keyboard;
	private Mesh mesh;

	public Render(KeyboardManager keyboard) {
		this.gl = null;
		this.keyboard = keyboard;
		this.mesh = new Mesh();
		this.mesh.criarMesh("OBJ\\Caveira.obj");
	}

	@Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
    	processarTeclas();
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);       
        gl.glLoadIdentity();
        
        gl.glLineWidth(2.0f);
        gl.glColor3f(0.0f, 1.0f, 0.6f);

        if (mesh != null && mesh.getFaces() != null && mesh.getVertices() != null) {
            ArrayList<Ponto3D> vertices = mesh.getVertices();
            int idVertice;
            
            for (Face face : mesh.getFaces()) { 
                gl.glBegin(GL2.GL_LINE_LOOP); 

                for (int posVertice: face.getPosVertices()) { 
                    if(posVertice > 0) {
                    	idVertice = posVertice - 1;
                    }
                    else {
                    	idVertice = posVertice;
                    }
                                        
                    if (idVertice >= 0 && idVertice < vertices.size()) {
                        Ponto3D p = vertices.get(idVertice);
                        gl.glVertex3d(p.getX(), p.getY(), p.getZ());
                    }
                }
                
                gl.glEnd();
            }
        }

        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height <= 0){
        	height = 1;
        }
        
        this.width = width;
        this.height = height;
        
        double aspectRatio = (double) width / height;
        
        gl.glViewport(0, 0, width, height);
        
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        
        
        // só da pra ver a translação no eixo Z se tiver essa bomba aqui, resolver depois
        //gl.glRotated(30, 1, 0, 0);
        //gl.glRotated(30, 0, 1, 0);
       
        if (aspectRatio >= 1.0) {
            gl.glOrtho(-10 * aspectRatio, 10 * aspectRatio, -10, 10, -100.0, 100.0);
        } 
        else{
            gl.glOrtho(-10, 10, -10 / aspectRatio, 10 / aspectRatio, -100.0, 100.0);
        }
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
    
    private void processarTeclas() {
        if (mesh == null || mesh.getVertices() == null) { 
        	return;
        }
        
        ArrayList<Ponto3D> vertices = mesh.getVertices();

        if (keyboard.teclaPressionada(KeyEvent.VK_D)) Transformacoes.translate(vertices, 0.05f, 0, 0);
        if (keyboard.teclaPressionada(KeyEvent.VK_A)) Transformacoes.translate(vertices, -0.05f, 0, 0);
        if (keyboard.teclaPressionada(KeyEvent.VK_W)) Transformacoes.translate(vertices, 0, 0.05f, 0);
        if (keyboard.teclaPressionada(KeyEvent.VK_S)) Transformacoes.translate(vertices, 0, -0.05f, 0);
        if (keyboard.teclaPressionada(KeyEvent.VK_Q)) Transformacoes.translate(vertices, 0, 0, 0.25f);
        if (keyboard.teclaPressionada(KeyEvent.VK_E)) Transformacoes.translate(vertices, 0, 0, -0.25f);

        // Cima baixo
        if (keyboard.teclaPressionada(KeyEvent.VK_NUMPAD8)) Transformacoes.rotate(vertices, 2.0f, new Vector3D(1.0f, 0.0f, 0.0f));
        if (keyboard.teclaPressionada(KeyEvent.VK_NUMPAD5)) Transformacoes.rotate(vertices, -10.0f, new Vector3D(1.0f, 0.0f, 0.0f));
        
        // Esquerda Direita
        if (keyboard.teclaPressionada(KeyEvent.VK_NUMPAD6))  Transformacoes.rotate(vertices, 2.0f, new Vector3D(0.0f, 1.0f, 0.0f));
        if (keyboard.teclaPressionada(KeyEvent.VK_NUMPAD4)) Transformacoes.rotate(vertices, -2.0f, new Vector3D(0.0f, 1.0f, 0.0f));
        
        // Proprio eixo
        if (keyboard.teclaPressionada(KeyEvent.VK_NUMPAD9)) Transformacoes.rotate(vertices, 2.0f, new Vector3D(0.0f, 0.0f, 1.0f));
        if (keyboard.teclaPressionada(KeyEvent.VK_NUMPAD7)) Transformacoes.rotate(vertices, -2.0f, new Vector3D(0.0f, 0.0f, 1.0f));


        if (keyboard.teclaPressionada(KeyEvent.VK_PLUS) || keyboard.teclaPressionada(KeyEvent.VK_EQUALS)) {
            Transformacoes.scale(vertices, 1.02f);
        }
        if (keyboard.teclaPressionada(KeyEvent.VK_MINUS)) {
            Transformacoes.scale(vertices, 0.98f);
        }
        
        if(keyboard.teclaPressionada(KeyEvent.VK_Z)) {
        	// tem que resetar manualmente pq o joptionpane buga o listener
        	keyboard.resetarTecla(KeyEvent.VK_Z);
        	String caminho = javax.swing.JOptionPane.showInputDialog("Digite o nome do arquivo para abrir");
        	caminho = "OBJ\\" + caminho;
        	
        	this.mesh = new Mesh();
        	this.mesh.criarMesh(caminho);
        	
        }
    }
}