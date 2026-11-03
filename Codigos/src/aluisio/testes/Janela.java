package aluisio.testes;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

import aluisio.codigos.KeyboardManager;

public class Janela {
	private int largura;
	private int altura;
	private Render renderer;
	private KeyboardManager keyboard;
	
	
	public Janela(int largura, int altura) {
		this.largura = largura;
		this.altura = altura;
		this.keyboard = new KeyboardManager();
		this.renderer = new Render(keyboard);
	}
	
	public void run() {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLJPanel panel = new GLJPanel(capabilities);
        
        this.keyboard.setComponente(panel);
        
        panel.addKeyListener(keyboard);
        panel.setFocusable(true);
        panel.addGLEventListener(renderer);
        panel.setSize(largura, altura);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(largura, altura);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
		
	}
	
}
