package ar.edu.utn.frc.backend;

public class Ciclo {

	/**
	 * Crear un método que reciba un array de números y devuelva la suma de todos
	 *
	 * @param numeros Array de números
	 * @return La suma de todos los números
	 */
	public static int calcularTotal(int[] numeros) {
		int suma = 0;
		for(int i = 0; i < numeros.length; i++){
			suma += numeros[i];
		}
		return suma;
	}

	/**
	 * Crear un método que reciba un número y devuelva su factorial
	 *
	 * @param numero Número del cual se quiere obtener el factorial
	 * @return El factorial del número
	 */
	public static int obtenerFactorial(int numero) {
		if (numero == 0) {
			return 1;
		}
		return numero * obtenerFactorial(numero - 1);
		
	}

	/**
	 * Crear un método que reciba un array de números y devuelva la cantidad de números pares
	 *
	 * @param numeros Array de números
	 * @return La cantidad de números pares
	 */
	public static int calcularCantidadDeNumerosPares(int[] numeros) {
		int cant = 0;
		for(int i = 0; i < numeros.length; i++){
			if(numeros[i] % 2 == 0){
				cant++;
			}
		}
		return cant;
	}
}
