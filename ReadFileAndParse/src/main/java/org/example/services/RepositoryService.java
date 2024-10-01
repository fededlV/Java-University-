package org.example.services;

import org.example.Main;
import org.example.domain.models.Language;
import org.example.domain.models.Repository;
import org.example.domain.models.Tag;
import org.example.domain.models.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepositoryService {

    //Method to parse a line and create a Repository object.
    public Repository mapRepository(String line) {
        String[] values = line.split("\\|");

        Integer id = Integer.parseInt(values[0]);
        String owner = values[1];
        String repoName = values[2];
        String desc = values[3];
        LocalDateTime lastUpdate = LocalDateTime.parse(
                values[4],
                DateTimeFormatter.ISO_DATE_TIME);
        Set<Language> languages = parseLanguages(values[5]);
        double stars = Double.parseDouble(values[6]);
        Set<Tag> tags = parseTags(values[7]);
        String url = values[8];
        User user = new User(owner);

        return new Repository(
                id,
                repoName,
                desc,
                lastUpdate,
                stars,
                url,
                user,
                tags,
                languages
        );
        }

        //Method to parse languages from a line
        public Set<Language> parseLanguages(String values) {
        if(values.trim().isEmpty()) {
            return Collections.emptySet();
        }
        return Arrays.stream(values.split(","))
                .map(Language::new)//Create new instance to language
                .collect(Collectors.toSet());
        }

        //Method to parse Tag from a line
        public Set<Tag> parseTags(String values) {
        if(values.trim().isEmpty()) {
            return Collections.emptySet();
        }
        return Arrays.stream(values.split(","))
                .map(Tag::new)//Create new instance to Tag
                .collect(Collectors.toSet());
        }
}
