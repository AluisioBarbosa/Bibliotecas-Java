package aluisio.codigos.math;

public class Matriz {

	public static float[][] mul(float x[][], float y[][]){
		if(x == null || y == null) {
			throw new NullPointerException("Matriz nula");
		}
		
		if(x.length == 0 || x[0].length == 0 || y.length == 0 || y[0].length == 0) {
			throw new IllegalArgumentException("Matriz com tamanho zero");
		}
		
		int linhaX = x.length;
		int colunaX = x[0].length;
		int linhaY = y.length;
		int colunaY = y[0].length;
		
		if(colunaX != linhaY) {
			throw new IllegalArgumentException("Matrizes de tamanho incompativel");
		}
		
		
		float matrizResultante[][] = new float[linhaX][colunaY];
		
		for(int i = 0; i < linhaX; i++) {
			for(int j = 0; j < colunaY; j++) {
				for(int k = 0; k < colunaX; k++) {
					matrizResultante[i][j] += x[i][k] * y[k][j];
				}
			}
		}
		
		return matrizResultante;
	}
	
	public static float[][] mul(float x[][], float escalar){
		if(x == null) {
			throw new NullPointerException("Matriz nula");
		}
		
		if(x.length == 0 || x[0].length == 0 ) {
			throw new IllegalArgumentException("Matriz com tamanho zero");
		}
		
		int linhaX = x.length;
		int colunaX = x[0].length;
		
		for(int i = 0; i < linhaX; i++) {
			for(int j = 0; j < colunaX; j++) {
				x[i][j] *= escalar;
			}
		}
		
		return x;
	}
	
	public static float[][] add(float x[][], float y[][]){
		if(x == null || y == null) {
			throw new NullPointerException("Matriz nula");
		}
		
		if(x.length == 0 || x[0].length == 0 || y.length == 0 || y[0].length == 0) {
			throw new IllegalArgumentException("Matriz com tamanho zero");
		}
		
		int linhaX = x.length;
		int colunaX = x[0].length;
		int linhaY = y.length;
		int colunaY = y[0].length;
		
		if(linhaX != linhaY) {
			throw new IllegalArgumentException("Matrizes de tamanho incompativel");

		}
		if(colunaX != colunaY) {
			throw new IllegalArgumentException("Matrizes de tamanho incompativel");
		}
		
		float[][] matrizResultante = new float[linhaX][colunaX];
		
		for(int i = 0; i < linhaX; i++) {
			for(int j = 0; j < colunaX; j++) {
				matrizResultante[i][j] = x[i][j] + y[i][j];
			}
		}
		
		return matrizResultante;
	}
	
	public static float[][] sub(float x[][], float y[][]){
		if(x == null || y == null) {
			throw new NullPointerException("Matriz nula");
		}
		
		if(x.length == 0 || x[0].length == 0 || y.length == 0 || y[0].length == 0) {
			throw new IllegalArgumentException("Matriz com tamanho zero");
		}
		
		int linhaX = x.length;
		int colunaX = x[0].length;
		int linhaY = y.length;
		int colunaY = y[0].length;
		
		if(linhaX != linhaY) {
			throw new IllegalArgumentException("Matrizes de tamanho incompativel");

		}
		if(colunaX != colunaY) {
			throw new IllegalArgumentException("Matrizes de tamanho incompativel");
		}
		
		float[][] matrizResultante = new float[linhaX][colunaX];
		
		for(int i = 0; i < linhaX; i++) {
			for(int j = 0; j < colunaX; j++) {
				matrizResultante[i][j] = x[i][j] - y[i][j];
			}
		}
		
		return matrizResultante;
	}
	
	public static float[][] transporMatriz(float x[][]){
		if(x == null) {
			throw new NullPointerException("Matriz nula");
		}
		
		if(x.length == 0 || x[0].length == 0 ) {
			throw new IllegalArgumentException("Matriz com tamanho zero");
		}
		
		int linhaX = x.length;
		int colunaX = x[0].length;

		
		float[][] matrizTransposta = new float[colunaX][linhaX];
		
		
		for(int i = 0; i < linhaX; i++) {
			for(int j = 0; j < colunaX; j++) {
				matrizTransposta[j][i] = x[i][j];
			}
		}
		
		return matrizTransposta;
	}
	
	public static float determinanteMatriz(float x[][]) {
		if(x == null) {
			throw new NullPointerException("Matriz nula");
		}
		if(x.length == 0 || x[0].length == 0) {
			throw new IllegalArgumentException("Matriz com tamanho zero");
		}
		
		int linhaX = x.length;
		int colunaX = x[0].length;
		if(linhaX != colunaX) {
			throw new IllegalArgumentException("Matriz com tamanho invalido");
		}
		
		float determinante = 1;
		
		x = escalonarMatrizGauss(x);
		
		for(int i = 0; i < x.length; i++) {
			determinante *= x[i][i];
		}
		
		return determinante;
	}
	
	public static float[] eliminacaoDeGauss(float[][] matriz) {
		if(matriz == null) {
			throw new NullPointerException("Matriz nula");
		}
		if(matriz.length == 0) {
			throw new IllegalArgumentException("Matriz com tamanho zero");
		}
		
        int n = matriz.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
            	float pivot = matriz[j][i] / matriz[i][i];
                
                for (int k = i; k < n + 1; k++) {
                    matriz[j][k] = matriz[j][k] - pivot * matriz[i][k];
                }
            }
        }

        float[] x = new float[n];
        x[n - 1] = matriz[n - 1][n] / matriz[n - 1][n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            x[i] = matriz[i][n];
            
            for (int j = i + 1; j < n; j++) {
                x[i] = x[i] - matriz[i][j] * x[j];
            }
            
            x[i] = x[i] / matriz[i][i];
        }

        return x;
    }	
	
	public static float[][] escalonarMatrizGauss(float[][] matriz){
		if(matriz == null) {
			throw new NullPointerException("Matriz nula");
		}
		if(matriz.length == 0) {
			throw new IllegalArgumentException("Matriz com tamanho invalido");
		}
		
		int n = matriz.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
   
            	// se o pivo for zero acha uma linha para trocar
            	if (matriz[i][i] == 0) {
                    int linha = i + 1;

                    while (linha < n && matriz[linha][i] == 0) {
                        linha++;
                    }

                    if (linha == n) {
                        continue;
                    }

                    float[] temp = matriz[i];
                    matriz[i] = matriz[linha];
                    matriz[linha] = temp;
                }
            	
            	float pivot = matriz[j][i] / matriz[i][i];
                
                for (int k = i; k < n; k++) {
                    matriz[j][k] = matriz[j][k] - pivot * matriz[i][k];
                }
            }
        }
        
        return matriz;
	}	
	
}