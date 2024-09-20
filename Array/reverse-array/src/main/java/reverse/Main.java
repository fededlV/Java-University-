package reverse;

public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 5, 4};
        
        System.out.println("Original array:");
        for (int number : arr) {
            System.out.print(number + " ");    
        }

        System.out.println("\nReverse Array:");
        for (int i = arr.length - 1 ; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }

    }
}