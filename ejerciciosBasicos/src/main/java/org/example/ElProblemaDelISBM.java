package org.example;

import java.util.Scanner;

public class ElProblemaDelISBM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char caracter;
        int resultado = 0;
        int valores = 0;
        int cantidad = 10;
        System.out.println("Ingresar ISBN: ");
        String isbn = sc.nextLine();
        for(int i = 0; i < isbn.length(); i++){
            caracter = isbn.charAt(i);
            if(caracter != '-') {
                valores = Character.getNumericValue(caracter);
                resultado += valores * cantidad;
                cantidad--;
            }
        }
        if(resultado % 11 == 0){
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
