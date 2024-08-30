package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class EjercicioUno {
    public static void main(String[] args) {
        //Hacer programa que pida al usuario un vector, que ingrese la cantidad. Mostrar promedio, mostrarlo ordenado.
        int[] v1 = new int[5];
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < v1.length; i++){
            System.out.print("Ingrese un numero: ");
            v1[i] = sc.nextInt();
        }

        int val = 0;
        for (int suma = 0; suma < v1.length; suma++) {
            val += v1[suma];
        }

        for (int i = 0; i < v1.length; i++){
            for(int j = 0; j < v1.length - 1 - i; j++){
                if(v1[j] > v1[j+1]){
                    int aux = v1[j];
                    v1[j] = v1[j+1];
                    v1[j+1] = aux;
                }
            }
        }
        System.out.println("Array ordenado: ");
        for(int i = 0; i < v1.length; i++){
            System.out.println(v1[i] + " ");
        }

        System.out.println("La suma de los numeros es: " + val);
        double prom = val / v1.length;
        System.out.println("El promedio es: " + prom);

    }
}
