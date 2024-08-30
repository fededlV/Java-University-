package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int v[] = new int[8];

        String[] arrStr = new String[]{
                "Cordoba", "Santa fe", "Buenos Aires", "La Rioja"
        };

        int n = v.length;

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i<n;i++){
            System.out.print("Ingrese el valor: ");
            v[i] = sc.nextInt();
        }
        for(int i = 0; i<n;i++){
            System.out.print(
                    String.format(" v[%d]=%d ", i, v[i])
            );
        }
        for(int val : v){
            System.out.println(val + " ");
        }
        for(String s : arrStr){
            System.out.println(s);
        }
    }
}