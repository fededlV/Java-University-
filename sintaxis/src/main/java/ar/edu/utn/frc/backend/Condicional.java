package ar.edu.utn.frc.backend;

public class Condicional {


	/**
	 * Devuelve el número mayor de los dos recibidos por parámetro, si son iguales devuelve -1
	 *
	 * @param numero          El primer número
	 * @param numeroAComparar El segundo número
	 * @return El número mayor de los dos recibidos por parámetro, si son iguales devuelve -1
	 */
	public static int obtenerMayorNumero(int numero, int numeroAComparar) {
		if(numero > numeroAComparar) {
			return numero;
		} else if (numero < numeroAComparar) {
			return numeroAComparar;
		} else {
			return -1;
		}
	}


	/**
	 * Devolver el color correspondiente al número recibido por parámetro
	 * 1: Rojo
	 * 2: Verde
	 * 3: Azul
	 * De lo contrario devolver "No es un color válido"
	 *
	 * @param numero El número del cual se quiere obtener el color
	 * @return El color correspondiente al número recibido por parámetro
	 */
	public static String obtenerColor(int numero) {
		if(numero == 1) {
			return "Rojo";
		} else if (numero == 2) {
			return "Verde";
		} else if (numero == 3) {
			return "Azul";
		} else {
			return "No es un color válido";
		}
		
	}
}
