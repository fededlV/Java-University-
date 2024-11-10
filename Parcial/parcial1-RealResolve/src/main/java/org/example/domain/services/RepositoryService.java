package org.example.domain.services;

import org.example.domain.model.Language;
import org.example.domain.model.Repository;
import org.example.domain.model.Tag;
import org.example.domain.model.User;
import org.example.domain.repository.RepositoryRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class RepositoryService {
    private RepositoryRepository repositoryRepository;

    public RepositoryService(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    public void saveAll(Set<Repository> repositorySet) {
        repositoryRepository.saveAll(repositorySet);
    }

    public List<Repository> findByUser(String userName) {
        return repositoryRepository.findByUser(userName);
    }

    public List<Repository> findByLanguage(String language) {
        return repositoryRepository.findByLanguage(language);
    }

    public List<Repository> findByTag(String tagName) {
        return repositoryRepository.findByTag(tagName);
    }

    public Set<Repository> loadRepository() {
        //To mantain an instance of tag, user and language, Requiriment 2.
        Map<String, User> userMap = new HashMap<>();
        Map<String, Language> languageMap = new HashMap<>();
        Map<String, Tag> tagMap = new HashMap<>();

        try(Stream<String> lines = Files.lines(Path.of(ClassLoader.getSystemResource("REPOSITORIES.txt")
                .toURI()))) {

            return lines.skip(1)
                    .map((line) -> mapRepository(line, userMap, languageMap, tagMap))
                    .collect(Collectors.toSet());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Repository mapRepository(String line,
                                     Map<String, User> usersMap,
                                     Map<String, Language> languagesMap,
                                     Map<String, Tag> tagsMap) {
        String[] values = line.split("\\|");

        Integer id = Integer.parseInt(values[0]);
        String userName = values[1];
        String repoName = values[2];
        String description = values[3];
        LocalDateTime lastUpdate = LocalDateTime.parse(values[4], DateTimeFormatter.ISO_DATE_TIME);
        Set<Language> languages = parseLanguages(values[5], languagesMap);
        Double stars = Double.parseDouble(values[6]);
        Set<Tag> tags = parseTags(values[7], tagsMap);
        String url = values[8];
        User user = usersMap.getOrDefault(userName, new User(userName));

        Repository repository = new Repository(
                id,
                repoName,
                description,
                lastUpdate,
                stars,
                url,
                user,
                tags,
                languages
        );

        //We saved the user after create Repository, for have the repo into User(its add into repo constructor)
        usersMap.put(userName, user);
        //Same after repo add into each Language, it's save into processed languages.
        languages.forEach((language) -> languagesMap.put(language.getName(), language));
        return repository;
    }

    private <T> Set<T> parseValues(String values,
                                   Map<String, T> item,
                                   Function<String, T> createNewItem ) {
        return Arrays.stream(values.split(","))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .map(itemName -> item.computeIfAbsent(itemName, createNewItem))
                .collect(Collectors.toSet());
    }

    private Set<Language> parseLanguages(String languages, Map<String, Language> languageMap) {
        return parseValues(languages, languageMap, Language::new);
    }

    private Set<Tag> parseTags(String tags, Map<String, Tag> tagMap) {
        return parseValues(tags, tagMap, Tag::new);
    }

    public void generateRepositoriesReport(String filePath, Set<Language> languages, double upperLimit) {
        File file = new File(filePath);

        try (PrintWriter printWriter = new PrintWriter(file)){
            languages.forEach((language) -> {
                printWriter.println(format("%s,%s,%s",
                language.getName(),
                        language.totalRepository(),
                        language.numberOfStars(upperLimit)
                        ));
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
