package org.example;

public class Bidim {
    public static void main(String[] args) {
        int[][] n = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}

        };

        for (int i = 0; i<n.length; i++){
            for (int j = 0; j<n[i].length; j++){
                System.out.printf("m[%d][%d] ", i, j, n[i][j]);
            }
        }
    }
}

