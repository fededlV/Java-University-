package org.example;

import java.io.File;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        int a, b = 8;
        float f1, f2;
        char car1 = 'A';
        String saludo = "Hola";
        boolean bVal = true;
        int val = 500_000; //Se pueden poner "_" en los literales.
        long val2 = 5_000_000_000L; // Para que al literal (numero escrito en el codigo) se lo interprete como Long es ponerle una L al final.
        int val3 = (int) 5_000_000_000L; //Se fuerza a que este long entre dentro del tipo int, aunque se pasen a numeros negativos o algo asi.
        //System.out.println(val3);
        var val4 = 121; //El compilador infiere el tipo de dato, utilizando "var"
        var val5 = "Hi there!";

        /* --------------------------------------------------------------------------------------------------- */

        //Scanner sc = new Scanner(System.in); //Scanner permite leer un mensaje ingresado por la terminal.
        /* System.out.print("Ingresar numero:");
        int valor = sc.nextInt();
        System.out.println("Valor: " + valor); */

        /* if (valor % 2 == 0){
            System.out.println("Es par!!");
            System.out.println("feliz");
        } else {
            System.out.println("Es impar!!");
        } */

        //Ternario
        //String resultado = (valor % 2 == 0) ? "Par!" : "Impar";

        //Switch Condicional multiple.
        /* switch (valor) {
            case 1: {
                System.out.println("Es par");
                break;
            }
            case 2: System.out.println("Es impar");

            default: {
                System.out.println("Opcion no encontrada");
            }
        } */

        /*
         ------------------------------ Ciclos-------------------------------------------------------------------- */

/*         for(int i = 0; i < 5; i++){
            System.out.println(i);
        }

        while(valor>10) {
            System.out.println("Val:" + valor);
            valor -= 1;
        }

        do {
            System.out.println("Valor: " + valor);
            valor -= 10;
        } while (valor > 10);

        sc.close(); */

        /* ----------------------------------------Lectura archivos ------------------------------------------------ */
        throw FileNotFoundException {
            var sc = new Scanner(new File("numero.txt"));

            while(sc.hasNext()) {
                System.out.println(sc.nextInt());
            }

            sc.close();


        }
    }
}