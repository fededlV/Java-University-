package org.example.domain.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "languages")
    private Set<Repository> repositories;

    public Language() {}

    public Language(String name) {
        this.name = name;
        this.repositories = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
    }

    protected  void addRepository(Repository repository) {
        this.repositories.add(repository);
    }

    public int totalRepository() {
        return repositories.size();
    }

    public long numberOfStars(double upperLimit) {
        return repositories
                .stream()
                .mapToLong((repository) -> repository.betweenZeroAndFive(upperLimit))
                .sum();
    }


    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", repositories=" + repositories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) && Objects.equals(name, language.name) && Objects.equals(repositories, language.repositories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, repositories);
    }
}
