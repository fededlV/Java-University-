package ar.edu.utn.frc.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Lectura {

	/**
	 * Crear un método que reciba la dirección de un archivo de texto y devuelva el total de los números que contiene
	 *
	 * @param direccionArchivo Dirección del archivo de texto
	 * @return El total de los números que contiene
	 * @throws FileNotFoundException
	 */
	public static int calcularTotal(String direccionArchivo) throws FileNotFoundException {
		int total = 0;
		var sc = new Scanner(new File(direccionArchivo));
		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				total += sc.nextInt();
			} else {
				sc.next();
			}
		}
		return total;
	}

	/**
	 * Crear un método que reciba la dirección de un archivo de texto y devuelva el promedio de los números que contiene
	 *
	 * @param direccionArchivo Dirección del archivo de texto
	 * @return El promedio de los números que contiene
	 * @throws FileNotFoundException
	 */
	public static float calcularPromedio(String direccionArchivo) throws FileNotFoundException {
		int total = 0;
		int cant = 0;
		var sc = new Scanner(new File(direccionArchivo));
		while (sc.hasNext()) {
			if (sc.hasNextInt()){
				total += sc.nextInt();
				cant++;
			} else {
				sc.next();
			}
		}
		return (float) total / cant;
	}
}
