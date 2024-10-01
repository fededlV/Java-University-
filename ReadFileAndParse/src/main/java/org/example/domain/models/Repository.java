package org.example.domain.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Repository {
    private  int id;
    private String repositoryName;
    private String description;
    private LocalDateTime lastUpdate;
    private double stars;
    private String url;
    private User user;
    private Set<Tag> tags;
    private Set<Language> languages;

    public Repository(int id, String repositoryName, String description, LocalDateTime lastUpdate, double stars, String url, User user, Set<Tag> tags, Set<Language> languages) {
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

    public long starsBetweenZeroAndFive(double upperLimit) {
        return Math.round((stars / upperLimit) * 5);
    }

    public void updateDescription(String newDescription) {
        description = newDescription;
        lastUpdate = LocalDateTime.now();
    }

    public void addStar() {
        stars++;
        lastUpdate = LocalDateTime.now();
    }

    public void removeStar() {
        stars--;
        lastUpdate = LocalDateTime.now();
    }

    public long lastUpdateFromNowInDays() {
        return Math.abs(
                Duration.between(
                        this.lastUpdate, LocalDateTime.now()).toDays()
                );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        if(tags == null) {
            tags = new HashSet<>();
        }
        tags.add(tag);
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public void addLanguages(Language language) {
        if(languages == null) {
            languages = new HashSet<>();
        }
        languages.add(language);
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
        return id == that.id && Double.compare(stars, that.stars) == 0 && Objects.equals(repositoryName, that.repositoryName) && Objects.equals(description, that.description) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(url, that.url) && Objects.equals(user, that.user) && Objects.equals(tags, that.tags) && Objects.equals(languages, that.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, repositoryName, description, lastUpdate, stars, url, user, tags, languages);
    }
}
