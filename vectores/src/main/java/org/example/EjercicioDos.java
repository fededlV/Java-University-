package org.example;

import java.util.Scanner;

public class EjercicioDos {
    public static void main(String[] args) {
        int[][] m1 = {
                {1,2,3},
                {3,2,1}
        };

        int[][] m2 = {
                {4,1},
                {8,1},
                {2,1}
        };

        int[][] m3 = new int[2][2];

        int a = 0;
        for (int i = 0; i < m3.length; i++){
            for(int j = 0; j < m3[i].length; j++) {
                for(int k = 0; k < m2.length; k++){
                  a += m1[i][k] * m2[k][j];
                }
                m3[i][j] = (int) a;
                a = 0;
            }
        }

        /*private static void mostrar(int[][] m;){
            for (int i = 0; i < m.length; i++) {
                System.out.println();
                for (int j = 0; j < m[i].length; j++) {
                    System.out.print(m[i][j] + " ");
                    System.out.println("");
                }

            }*/

    }
}


