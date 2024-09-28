package org.example;


import java.util.Arrays;

public class Main {
        public static void reverse(int[] arr, int start, int end) {
            while(start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }

        public static  void rotateRight(int[] arr, int k) {
            int n = arr.length;
            k = k % n;

            //Reverse all array
            reverse(arr, 0, n - 1);

            //Reverse the first "k" elements
            reverse(arr, 0, k - 1);

            //Reverse the remaining "n - k" elements
            reverse(arr, k, n - 1);
        }
    public static void main(String[] args) {
         int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
         int k = 3;

        rotateRight(arr, k);

        System.out.println("Array after rotation: " + Arrays.toString(arr));
    }
}