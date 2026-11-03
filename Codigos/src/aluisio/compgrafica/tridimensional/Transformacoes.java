package aluisio.compgrafica.tridimensional;

import java.util.ArrayList;

import aluisio.codigos.math.Matriz;

public class Transformacoes {

	
	public static void translate(ArrayList<Ponto3D> pontos, float dx, float dy, float dz) {
		for(Ponto3D p: pontos) {
			p.setX(p.getX() + dx);
            p.setY(p.getY() + dy);
            p.setZ(p.getZ() + dz);
		}
	}
	
	private static Ponto3D calcularCentroDeMassa(ArrayList<Ponto3D> pontos) {
        if (pontos.isEmpty()) {
        	return new Ponto3D(0, 0, 0);
        }
        float sumX = 0;
        float sumY = 0;
        float sumZ = 0;
        
        for (Ponto3D p : pontos) {
            sumX += p.getX();
            sumY += p.getY();
            sumZ += p.getZ();
        }
        return new Ponto3D(sumX / pontos.size(), sumY / pontos.size(), sumZ/pontos.size());
    }
	
	public static void rotate(ArrayList<Ponto3D> pontos, float angulo, Vector3D v1) {
		Ponto3D centro = calcularCentroDeMassa(pontos);
		translate(pontos, -centro.getX(), -centro.getY(), -centro.getZ());
		
		float anguloRadianos = (float) Math.toRadians(angulo);
		float seno = (float) Math.sin(anguloRadianos);
		float cos = (float) Math.cos(anguloRadianos);
		float umCos = 1 - cos;
		v1.normalize();
		
		
		float x = v1.getX();
		float y = v1.getY();
		float z = v1.getZ();
		float x2 = v1.getX() * v1.getX();
		float y2 = v1.getY() * v1.getY();
		float z2 = v1.getZ() * v1.getZ();

		
		float[][] matrizRotacao = { {(cos + x2 *(umCos)), 		(x*y*(umCos) - z*seno), 		(x*z*(umCos) + y*seno), 		0},
									{(y*x*(umCos) + z*seno), 	(cos + y2*(umCos)), 			(y*z*(umCos) - x*seno), 		0},
									{(z*x*(umCos) - y*seno), 	(z*y*(umCos) + x*seno), 		(cos + z2*(umCos)), 			0},
									{0, 						0, 								0, 								1}      };
		
		
		for(Ponto3D p: pontos) {
			float[][] matrizPontos = {	{p.getX()},
										{p.getY()},
										{p.getZ()},
										{1.0f}    };
			
			matrizPontos = Matriz.mul(matrizRotacao, matrizPontos);
			p.setX(matrizPontos[0][0]);
			p.setY(matrizPontos[1][0]);
			p.setZ(matrizPontos[2][0]);
		}
		
		
		translate(pontos, centro.getX(), centro.getY(), centro.getZ());
	}
	
	public static void scale(ArrayList<Ponto3D> pontos, float fatorX, float fatorY, float fatorZ) {
		Ponto3D centro = calcularCentroDeMassa(pontos);
		translate(pontos, -centro.getX(), -centro.getY(), -centro.getZ());
		
		float[][] matrizEscala = {  {fatorX, 0, 0, 0},
									{0, fatorY, 0, 0},
									{0, 0, fatorZ, 0},
									{0, 0, 0, 1}      };
		
		for(Ponto3D p: pontos) {
			float[][] matrizPontos = {	{p.getX()},
										{p.getY()},
										{p.getZ()},
										{1.0f}    };
			
			matrizPontos = Matriz.mul(matrizEscala, matrizPontos);
			p.setX(matrizPontos[0][0]);
			p.setY(matrizPontos[1][0]);
			p.setZ(matrizPontos[2][0]);
		}	
		translate(pontos, centro.getX(), centro.getY(), centro.getZ());
	}
	
	public static void scale(ArrayList<Ponto3D> pontos, float fator) {
		float[][] matrizEscala = {  {fator, 0, 0, 0},
									{0, fator, 0, 0},
									{0, 0, fator, 0},
									{0, 0, 0, 1}      };
		
		for(Ponto3D p: pontos) {
			float[][] matrizPontos = {	{p.getX()},
										{p.getY()},
										{p.getZ()},
										{1.0f}    };
			
			matrizPontos = Matriz.mul(matrizEscala, matrizPontos);
			p.setX(matrizPontos[0][0]);
			p.setY(matrizPontos[1][0]);
			p.setZ(matrizPontos[2][0]);
		}	
	}
	
	
}
