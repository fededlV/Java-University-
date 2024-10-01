package org.example.domain.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Repository> repositories;

    public User(String name) {
        this.name = name;
        repositories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public int totalRepositories() {
        return repositories.size();
    }

    public long totalStars(double upperLimit) {
        return this.repositories
                .stream()
                .mapToLong((repository) -> repository.starsBetweenZeroAndFive(upperLimit))
                .sum();
    }

    public void addRepositories(Repository repository) {
        repositories.add(repository);
    }
}
