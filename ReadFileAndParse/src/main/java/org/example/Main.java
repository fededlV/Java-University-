package org.example;


import org.example.domain.models.Repository;
import org.example.services.RepositoryService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        RepositoryService repositoryService = new RepositoryService(); //Instance of RepositoryService

        try {
            Path path = Path.of(ClassLoader.getSystemResource("REPOSITORIES.txt").toURI());
            try(Stream<String> lines = Files.lines(path)) {
                List<Repository> repositoryList = lines
                        .skip(1)
                        .map(repositoryService::mapRepository) //Mapea cada linea a un objeto repository
                        .collect(Collectors.toList()); //Recolecta los objetos en una lista

                //Aqui puedes ver los repositorios creados.
                repositoryList.forEach(System.out::println);
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}