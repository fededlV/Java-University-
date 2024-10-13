package org.example;

import java.util.List;

public class FPExercise3 {
    private static void printCoursesWithSpring(List<String> courses) {
        courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);
    }

    public static void main(String[] args) {
        printCoursesWithSpring(List.of("Spring" , "Spring Boot", "API", "Microservices"));
    }
}
