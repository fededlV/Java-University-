package org.example;

import java.nio.file.Paths;

import org.example.service.FileParser;

public class Main {
    public static void main(String[] args) {
        FileParser parser = new FileParser();
        parser.parseFile(Paths.get("src/main/REPOSITORIES.txt"));
        parser.printStatistics();
        parser.close();
    }
}