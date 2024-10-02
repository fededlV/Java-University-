package org.example.domain.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Repository> repositories;

    public User() {}

    public User(String name) {
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

    public int numberOfRepositories() {
        return this.repositories.size();
    }

    public long numberOfStars(double upperLimit) {
        return this.repositories.stream()
                .mapToLong((repository) -> repository.betweenZeroAndFive(upperLimit))
                .sum();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", repositories=" + repositories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(repositories, user.repositories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, repositories);
    }

    @Override
    public int compareTo(User user) {
        return this.name.compareTo(user.getName());
    }
}
