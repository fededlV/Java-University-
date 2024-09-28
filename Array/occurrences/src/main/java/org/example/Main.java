package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 5, 3};
        System.out.println("Ingresar numero: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(Arrays.stream(arr).filter(x -> x == n).count());
    }
}