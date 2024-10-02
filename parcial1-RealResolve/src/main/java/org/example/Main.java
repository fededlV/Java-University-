package org.example;

import org.example.domain.model.Language;
import org.example.domain.model.Repository;
import org.example.domain.services.RepositoryService;
import org.example.infraestructure.H2Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        RepositoryService repoService = new RepositoryService(new H2Repository());

        //Require 1
        Set<Repository> repositories = repoService.loadRepository();
        //System.out.println(repositories.toString());

        //Requierement 3
        System.out.println("Cantidad de repositorios importados: " + repositories.size());

        double upperLimit = repositories
                .stream()
                .mapToDouble(Repository::getStars)
                .max()
                .orElseThrow();

        long totalStars = repositories
                .stream()
                .mapToLong((repository) -> repository.betweenZeroAndFive(upperLimit))
                .sum();
        System.out.printf("El numero total de stars de todos los repos es: %s%n", totalStars);


        //Requeriment 4
        repoService.generateRepositoriesReport(
                "REPORT.txt",
                repositories.stream()
                        .flatMap((repository) -> repository.getLanguages().stream())
                        .collect(Collectors.toSet()),
                upperLimit
        );

        //Requerimiento 5
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nombre de usuario | Cantidad de repositorios | Total de estrellas")
                .append("\n");
        repositories
                .stream()
                .map(Repository::getUser)
                .distinct()
                .sorted()
                .forEach((user) ->{
                    long totalRepositories = user.numberOfRepositories();
                    long numberOfStars = user.numberOfStars(upperLimit);

                    stringBuilder.append(String.format("%s | %s | %s", user.getName(), totalRepositories, numberOfStars))
                            .append("\n");
                        });
        System.out.println(stringBuilder);

        //Requerimiento 6
        repoService.saveAll(repositories);

        //Consulta a la base de datos
        //Repositorios por usuario
        List<Repository> repositoryByUser = repoService.findByUser("freeCodeCamp");

        //Repositorios por lenguaje
        List<Repository> repositoyByLanguage = repoService.findByLanguage("JavaScript");

        //Repositorios por Tag.
        List<Repository> repositoryByTag = repoService.findByTag("nodejs");

        //Consultar la lista de repositorios por un usuario especifico y la lista de lenguajes que utiliza en todos sus repositorios.
        Set<Language> languagesUsedInAllUserRepositories = repositoryByUser
                .stream()
                .flatMap((repository) -> repository.getLanguages().stream())
                        .collect(Collectors.toSet());

        System.out.println("Fin");
    }
}