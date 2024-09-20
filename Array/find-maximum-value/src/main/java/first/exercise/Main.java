package first.exercise;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int max = array[0];

        for (int number : array) {
            if (number > max) {
                max = number;
            }
        }

        System.out.println("The maximum value in the array is: " + max);
    }
}