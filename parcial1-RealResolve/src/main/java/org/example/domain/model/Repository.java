package org.example.domain.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "repository")
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repository")
    private Integer id;
    @Column(name = "repository_name")
    private String repositoryName;
    private String description;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    private double stars;
    private String url;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tag_repository",
            joinColumns = @JoinColumn(name = "id_repository"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "language_repository",
            joinColumns = @JoinColumn(name = "id_repository"),
            inverseJoinColumns = @JoinColumn(name = "id_language")
    )
    private Set<Language> languages;

    public Repository() {}

    public Repository(Integer id, String repositoryName, String description, LocalDateTime lastUpdate, double stars, String url, User user, Set<Tag> tags, Set<Language> languages) {
        this.id = id;
        this.repositoryName = repositoryName;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.url = url;
        this.user = user;
        this.tags = tags;
        this.languages = languages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Language> getLanguages() {
        return Collections.unmodifiableSet(languages);
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long betweenZeroAndFive(double upperLimit) {
        return Math.round((stars / upperLimit) * 5);
    }

    public void updateDescription(String description) {
        this.description = description;
        this.lastUpdate = LocalDateTime.now();
    }

    public void addStars() {
        this.stars++;
        this.lastUpdate = LocalDateTime.now();
    }

    public void removeStars() {
        this.stars--;
        this.lastUpdate = LocalDateTime.now();
    }

    public long lastUpdateSinceNow() {
        return Math.abs(Duration.between(
                this.lastUpdate, LocalDateTime.now()
        ).toDays());
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public double getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }



    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", repositoryName='" + repositoryName + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", stars=" + stars +
                ", url='" + url + '\'' +
                ", user=" + user +
                ", tags=" + tags +
                ", languages=" + languages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Double.compare(stars, that.stars) == 0 && Objects.equals(id, that.id) && Objects.equals(repositoryName, that.repositoryName) && Objects.equals(description, that.description) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(url, that.url) && Objects.equals(user, that.user) && Objects.equals(tags, that.tags) && Objects.equals(languages, that.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, repositoryName, description, lastUpdate, stars, url, user, tags, languages);
    }
}
