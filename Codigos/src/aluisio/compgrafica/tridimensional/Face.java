package aluisio.compgrafica.tridimensional;

import java.util.ArrayList;

public class Face {
	private ArrayList<Integer> posVertices = new ArrayList<>();
	private ArrayList<Integer> posNormal = new ArrayList<>();
	private ArrayList<Integer> posTexturas = new ArrayList<>();
	
	public ArrayList<Integer> getPosVertices() {
		return posVertices;
	}
	public void setPosVertices(ArrayList<Integer> posVertices) {
		this.posVertices = posVertices;
	}
	public ArrayList<Integer> getPosNormal() {
		return posNormal;
	}
	public void setPosNormal(ArrayList<Integer> posNormal) {
		this.posNormal = posNormal;
	}
	public ArrayList<Integer> getPosTexturas() {
		return posTexturas;
	}
	public void setPosTexturas(ArrayList<Integer> posTexturas) {
		this.posTexturas = posTexturas;
	}
	
	
	
	
}
