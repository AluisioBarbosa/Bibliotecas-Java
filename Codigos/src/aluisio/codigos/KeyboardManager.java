package aluisio.codigos;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardManager extends KeyAdapter {
	private boolean[] teclas = new boolean[256];
    private Component componente;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < teclas.length) {
            teclas[keyCode] = true;
        }
        
        if (componente != null) {
            componente.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode < teclas.length) {
            teclas[keyCode] = false;
        }
    }
    
    public boolean teclaPressionada(int keyCode) {
        if (keyCode >= 0 && keyCode < teclas.length) {
            return teclas[keyCode];
        }
        return false;
    }
    
    
    public void resetarTecla(int keyCode) {
    	if(keyCode >= 0 && keyCode <= teclas.length) {
    		teclas[keyCode] = false;
    	}
    }
    
    public void resetarTeclas() {
        for (int i = 0; i < teclas.length; i++) {
            teclas[i] = false;
        }
    }

    public void setComponente(Component componente) {
        this.componente = componente;
    }
}