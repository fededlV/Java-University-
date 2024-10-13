package org.example;

import java.util.List;

public class FPExercise5 {
    private static void printCubesOfOddNumbers(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n * n)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        printCubesOfOddNumbers(List.of(1,2,3,4,5,6,7,78,7,8));
    }
}
