package org.example;

public class Main {
    public static void main(String[] args) {
        /**
         * El objetivo es lograr imprimir por pantalla las siguientes figuras pero solo pudiendo imprimir a la vez uno y solo un asterisco o uno y solo un espacio, es decir solo podemos imprimir una variable de tipo char que tenga como valor '*' o ' '.
         */
        System.out.println("Figura 1");
        for(int i = 0; i <= 3; i++){
            System.out.println("******");
        }

        System.out.println(" ");
        System.out.println("Figura 2");
        for(int i = 0; i <= 3; i++){
            if(i % 2 == 0){
                System.out.println(" ******");
            } else {
                System.out.println("******");
            }
        }

        System.out.println(" ");
        System.out.println("Figura 3");
        char caracter = '*';
        int times = 5;
        String result = "";
        for(int i = 0; i < times; i++){
            result += caracter;
            System.out.println(result);
        }

        System.out.println(" ");
        System.out.println("Figura 4");
        int times2 = 5;
        for(int i = 0; i <= times2; i++){
            for(int j = 0; j <= i; j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        for(int i = times2; i >= 1;i--){
            for(int j = 1; j <= i; j++){
                System.out.print("* ");
            }
            System.out.println();
        }

    }
}