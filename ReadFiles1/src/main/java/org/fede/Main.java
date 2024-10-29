package org.fede;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Mis Archivos\\Cursos Programacion\\Java-University-\\ReadFiles1/mascotas.csv");
        String line;
        String csvSplit = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            while((line = br.readLine()) != null) {
                String[] values = line.split(csvSplit);
                for (String value : values) {
                    System.out.println(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}