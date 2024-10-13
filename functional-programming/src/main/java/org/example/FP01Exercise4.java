package org.example;

import java.util.List;

public class FP01Exercise4 {

    private static void printCoursesWithAtLeast4Letters(List<String> courses) {
        courses.stream().filter(course -> course.length() >= 4).forEach(System.out::println);
    }

    public static void main(String[] args) {
        printCoursesWithAtLeast4Letters(List.of("Spring", "API", "Kafka", "AWS", "Microservices"));
    }
}
