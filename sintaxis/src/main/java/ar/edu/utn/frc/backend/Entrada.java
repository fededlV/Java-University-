package ar.edu.utn.frc.backend;

import java.util.Scanner;

public class Entrada {

	public static void main(String[] args) {
		int suma = 0;

		System.out.println("Ingrese una serie de números (ingrese un número negativo para detenerse):");

		//Agregar lógica acá
		Scanner sc = new Scanner(System.in);
		int numero = sc.nextInt();
		while (numero >= 0) {
			suma += numero;
			numero = sc.nextInt();
		}
		sc.close();

		System.out.println("La suma de los números positivos: " + suma);

	}
}
