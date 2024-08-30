package org.example;

public class EjercicioDos {
    public static void main(String[] args) {
        //Pedirle al usuario dos matrices, cargar esa matriz y responder multiplicacion de las dos matriz
        int[][][] m1 = new int[][][]{
                {{1,2,3},{4,5,6},{7,8,9}},
                {{1,2,3},{4,5,6},{7,8,9}},
                {{1,2,3},{4,5,6},{7,8,9}},
        };
        //int[][][] m2 = new int[3][3][3];

        for (int i = 0; i < m1.length; i++) {
            for(int j = 0; j < m1[i].length; j++) {
                for(int k = 0; k < m1[i][j].length; k++) {
                    System.out.print("matriz[" + i + "][" + j + "][" + k + "] = " + m1[i][j][k] + "\t");
                }
            }
        }

    }
}


