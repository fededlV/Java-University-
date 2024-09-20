package array.sum;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Random random = new Random();

        for(int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        int sum = 0;
        for (int number : arr) {
            sum += number;
        }

        System.out.println("Sum of array elements: " + sum);

    }
}