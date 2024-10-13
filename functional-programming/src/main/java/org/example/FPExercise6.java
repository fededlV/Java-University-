package org.example;

import java.util.List;

public class FPExercise6 {

    private static void printNumberOfCharactersInEachCourseName(List<String> courses) {
        courses.stream()
                .map(course -> course.length())
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        printNumberOfCharactersInEachCourseName(List.of("Spring", "Kafka", "API"));
    }
}
