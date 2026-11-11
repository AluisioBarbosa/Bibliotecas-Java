package aluisio.compgrafica.tridimensional;

public class Textura {
	private int idTex;
	private String Tipo;
	private String arquivo;
	
	public Textura(String arquivo) {
		this.arquivo = arquivo;
	}
	
	public int getIdTex() {
		return idTex;
	}
	public void setIdTex(int idTex) {
		this.idTex = idTex;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
}
