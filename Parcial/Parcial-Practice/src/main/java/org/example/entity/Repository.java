package org.example.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "backend.repository")
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(
        name = "repository_name",
        nullable = false
        )
    private String repositoryName;
    @Column(nullable = false)
    private String description;

    @Column(
        name = "last_update", 
        nullable = false
        )
    private LocalDateTime lastUpdate;
    
    @Column(nullable = false)
    private double stars;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(
        name = "owner_id",
        nullable = false
        )
    private User user;

    @ManyToMany
    @JoinTable(
        name = "backend.tag_repository",
        joinColumns = @JoinColumn(name = "id_repository"),
        inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "backend.language_repository",
        joinColumns = @JoinColumn(name = "id_repository"),
        inverseJoinColumns = @JoinColumn(name = "id_language")
    )
    private Set<Language> languages = new HashSet<>();

    

    public Repository() {
    }

    public Repository(Long id, String repositoryName, String description, LocalDateTime lastUpdate, double stars, String url) {
        this.id = id;
        this.repositoryName = repositoryName;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public double getStars() {
        double scaledStars = (stars / 100) * 5;
        return Math.round(scaledStars * 10) / 10.0;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public long getDaysSinceLastUpdate() {
        return ChronoUnit.DAYS.between(lastUpdate, LocalDateTime.now());
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /* public void setLanguage(Language language) {
        this.language = language;
        language.addRepository(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.addRepository(this);
    } */

    /* public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.addRepository(this);
    } */


}
