package org.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
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
    private EntityManagerFactory emf;
    private int totalRepositories = 0;
    private double totalStars = 0.0;

    public FileParser() {
        emf = Persistence.createEntityManagerFactory("gestorPersi");
    }

    public void parseFile(Path filePath) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Set<Tag> tags = new HashSet<>();
        Set<Language> languages = new HashSet<>();
        Set<User> users = new HashSet<>();

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
                User user = users.stream().filter(u -> u.getName().equals(userName)).findFirst().orElse(null);
                if(user == null) {
                    user = new User(userName);
                    users.add(user);
                    em.persist(user);
                } else {
                    user = em.merge(user); //Ensure the user is managed
                }

                Language language = languages.stream().filter(l -> l.getName().equals(languageNames)).findFirst().orElse(null);
                if (language == null) {
                    language = new Language(languageNames);
                    languages.add(language);
                    em.persist(language);
                } else {
                    em.merge(language); //Ensure the language is managed
                }

                //Create or get the language
                String[] tagNames = tagsString.split(",");
                //Iterate over the tag names and create or get the tags
                for (String tagName : tagNames) {
                    Tag tag = tags.stream().filter(t -> t.getName().equals(tagName)).findFirst().orElse(null);
                    if(tag == null) {
                        tag = new Tag(tagName);
                        tags.add(tag);
                        em.persist(tag);
                    } else {
                        tag = em.merge(tag); //Ensure the tag is managed
                    }
                }

                //Create the Repository
                Repository repository = new Repository(repositoryId, repoName, description, lastUpdate, stars, url);
                repository.setLanguages(languages);
                repository.setTags(tags);
                //Add the repository to the user
                user.addRepository(repository);
                em.persist(repository);

                //Update total repositories and stars
                totalRepositories++;
                totalStars += stars;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        emf.close();
    }

    public void printStatistics() {
        System.out.println("Total repositories: " + totalRepositories);
        System.out.println("Average stars: " + totalStars);
    }
}
