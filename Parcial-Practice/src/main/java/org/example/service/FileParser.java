package org.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;


import org.example.entity.Language;
import org.example.entity.Repository;
import org.example.entity.Tag;
import org.example.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FileParser {
    private Map<String, Tag> tags = new HashMap<>();
    private Map<String, Language> languages = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    private Set<Repository> repositories = new HashSet<>();

    private EntityManager em;
    

    public FileParser() {
        this.em = em;
    }

    public void parseFile(Path filePath) {
        em.getTransaction().begin();

        try(Stream<String> lines = Files.lines(filePath)) {
            lines.skip(1).forEach(line -> { //Skip the first line
                String[] parts = line.split("\\|");
                if (parts.length < 9) {
                    return;
                }

                Long repositoryId = Long.parseLong(parts[0]);
                String userName = parts[1];
                String repoName = parts[2];
                String description = parts[3];
                LocalDateTime lastUpdate = LocalDateTime.parse(parts[4], DateTimeFormatter.ISO_DATE_TIME);
                String languageNames = parts[5];
                double stars = Double.parseDouble(parts[6]);
                String tagsString = parts[7];
                String url = parts[8];

                //Create or get the user
                User user = users.computeIfAbsent(userName, u -> {
                    User newUser = new User(userName);
                    em.persist(newUser);
                    return newUser;
                });

                //Create new Repository and associate to user
                Repository repository = new Repository();
                repository.setRepositoryName(repoName);
                repository.setDescription(description);
                repository.setLastUpdate(lastUpdate);
                repository.setId(repositoryId);
                repository.setStars(stars);
                repository.setUrl(url);
                repository.setUser(user);
                user.addRepository(repository);
                

                //Create the languages
                Language language = languages.computeIfAbsent(languageNames, l -> {
                    Language newLanguage = new Language(languageNames);
                    em.persist(newLanguage);
                    return newLanguage;
                });
                repository.getLanguages().add(language);

                //Create or get the language
                String[] tagNames = tagsString.split(",");
                //Iterate over the tag names and create or get the tags
                for (String tagName : tagNames) {
                    Tag tag = tags.computeIfAbsent(tagName, t -> {
                        Tag newTag = new Tag(tagName);
                        em.persist(newTag);
                        return newTag;
                    });
                    repository.getTags().add(tag);
                }

                repositories.add(repository);
                em.persist(repository);
            });

            reportTotals();
        } catch (IOException e) {
            e.printStackTrace();
        }

        em.getTransaction().commit();
        em.close();
    }

    //Method to print the total of repos and stars.
    public void reportTotals() {
        int totalRepositories = repositories.size();
        double totalStars = repositories.stream().mapToDouble(Repository::getStars).sum();
        System.out.println("Total de repositorios importados: " + totalRepositories);
        System.out.println("Total de estrellas acumuladas: " + totalStars);
    }
}
