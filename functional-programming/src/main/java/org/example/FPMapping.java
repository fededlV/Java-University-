package org.example;

import java.util.List;

public class FPMapping {

    private static void printSquareOfEvenNumberInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 == 0) //Lambda expression
                .map(n -> n * n) //Mapping
                .forEach(System.out::println); //Method reference
    }

    public static void main(String[] args) {
        printSquareOfEvenNumberInListFunctional(List.of(1,2,3,4,5,6,7,1,12,4));
    }
}
