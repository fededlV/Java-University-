package org.example;

import java.util.List;

public class FPExercise2 {
    private static void printAllCourses(List<String> courses) {
        courses.forEach(System.out::println);
    }

    public static void main(String[] args) {
        printAllCourses(List.of("Java", "Python", "Spring", "I want to cut my balls"));
    }
}
