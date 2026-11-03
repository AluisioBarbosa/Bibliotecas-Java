	package aluisio.compgrafica.tridimensional;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Mesh {
	private ArrayList<Ponto3D> vertices = new ArrayList<>();
	private ArrayList<Vector3D> normais = new ArrayList<>();
	private ArrayList<Face> faces = new ArrayList<>();
	private ArrayList<TexCoord> coordenadasTextura = new ArrayList<>();
	
	public void criarMesh(String arquivo) {
		File f = new File(arquivo);
		try {
			Scanner scan = new Scanner(f);	
			scan.useLocale(Locale.US);
			while(scan.hasNext()) {
				String flag = scan.next();
				
				if(flag.equals("#")) {
					scan.nextLine();
					continue;
				}
				else if(flag.equals("v")) {
					float x = scan.nextFloat();
					float y = scan.nextFloat();
					float z = scan.nextFloat();
					vertices.add(new Ponto3D(x,y,z));
				}
				else if(flag.equals("vn")) {
					float x = scan.nextFloat();
					float y = scan.nextFloat();
					float z = scan.nextFloat();
					normais.add(new Vector3D(x,y,z));
				}
				else if(flag.equals("vt")) {
					float x = scan.nextFloat();
					float y = scan.nextFloat();
					coordenadasTextura.add(new TexCoord(x,y));
					
				}
				else if(flag.equals("f")) {
					Face face = new Face();
					for(int i = 0; i < 3; i++) {
						String valoresFace = scan.next();
						
						// vertice e normal
						if(valoresFace.contains("//")){
							String[] numeros = valoresFace.split("//");
							face.getPosVertices().add(Integer.parseInt(numeros[0]));
							face.getPosNormal().add(Integer.parseInt(numeros[1]));							
						}
						// vertice e textura
						else if(valoresFace.contains("/")) {
							String[] numeros = valoresFace.split("/");
							face.getPosVertices().add(Integer.parseInt(numeros[0]));
							face.getPosTexturas().add(Integer.parseInt(numeros[1]));
							
							// se tiver vertice, textura e normal
							if(numeros.length > 2) {
								face.getPosNormal().add(Integer.parseInt(numeros[2]));
							}
						}
						// só vertice
						else {
							face.getPosVertices().add(Integer.parseInt(valoresFace));
						}
					}
					faces.add(face);
				}
				// se tiver alguma flag desconhecida ou algo do tipo, só pula a linha
				else {
					scan.nextLine();
					continue;
				}
				
			}
			System.out.println(faces.size());
			System.out.println(coordenadasTextura.size());
			System.out.println(normais.size());
			scan.close();
		}
		catch(FileNotFoundException e){
			
		}
		
		
	}

	public ArrayList<Ponto3D> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Ponto3D> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Vector3D> getNormais() {
		return normais;
	}

	public void setNormais(ArrayList<Vector3D> normais) {
		this.normais = normais;
	}

	public ArrayList<Face> getFaces() {
		return faces;
	}

	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
	}

	public ArrayList<TexCoord> getCoordenadasTextura() {
		return coordenadasTextura;
	}

	public void setCoordenadasTextura(ArrayList<TexCoord> coordenadasTextura) {
		this.coordenadasTextura = coordenadasTextura;
	}
	
	

	
	
}
