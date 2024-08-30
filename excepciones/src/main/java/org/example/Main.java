package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File f = new File("algun_archivo.ext");

        try {
            Scanner sc = new Scanner(f);
            System.out.println("El archivo existe."); //No se ejecuta porque la primera linea no arroja lo correcto.
        } catch (FileNotFoundException nfe) { // Es del tipo
            System.out.println("El archivo no existe");
            nfe.printStackTrace(); //Muestra la traza de errores
        } catch (NullPointerException npe) { //Excepcion no chequeada, excepcion que no estas obligado a que salte en tu metodo
            System.out.println("Referencia nula"); //se pueden hacer todos los catchs que yo quiera.
        } finally { // Se lo hace si o si, no importa si hay error o no.
            System.out.println("Me Ejecuto siempre");
        }

        //Try with resources
        /*try(Scanner sc = new Scanner(f)){
            System.out.println("Scanner creado");
        }*/

    }
}