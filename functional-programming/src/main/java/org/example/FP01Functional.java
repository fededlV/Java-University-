package org.example;

import java.util.List;

public class FP01Functional {

    private static void printAllNumbersInListFunctional(List<Integer> numbers ) {
        numbers.stream()
                .forEach(System.out::println); //Method Reference
    }

    private static void printOddNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number % 2 != 0).forEach(System.out::println);
    }

    private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number % 2 == 0).forEach(System.out::println);
    }

    public static void main(String[] args) {
        printAllNumbersInListFunctional(List.of(1,2,3,4,5,6,7,534,2,123,21));
        System.out.println("");
        printOddNumbersInListFunctional(List.of(1,2,3,4,5,6,7,534,2,123,21));
        System.out.println("");
        printEvenNumbersInListFunctional(List.of(1,2,3,4,5,6,7,534,2,123,21));
    }
}
