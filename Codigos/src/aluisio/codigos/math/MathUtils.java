package aluisio.codigos.math;

public class MathUtils {
	
	public static double distanciaEuclidiana(double... x) {
		if (x == null || x.length == 0) {
			throw new IllegalArgumentException("Quantidade de numeros insuficientes para distancia euclidiana");
		}
		if((x.length % 2) != 0) {
			throw new IllegalArgumentException("Quantidade de numeros para operação deve ser multiplo de 2");
		}
		
		double distancia = 0;
		for(int i = 0; i < x.length; i = i + 2) {
			distancia +=  Math.pow(x[i] - x[i + 1], 2);
		}
		
		return Math.sqrt(distancia);
	}
	
}
