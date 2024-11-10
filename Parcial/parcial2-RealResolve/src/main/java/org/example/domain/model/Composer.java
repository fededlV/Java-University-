package org.example.domain.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "composers")
public class Composer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "composer_id")
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "composers")
    private Set<Track> tracks;

    public Composer(String name) {
        this.name = name;
        this.tracks = new HashSet<>();
    }

    public Composer() {}

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

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    @Override
    public String toString() {
        return "Composer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composer composer = (Composer) o;
        return Objects.equals(id, composer.id) && Objects.equals(name, composer.name) && Objects.equals(tracks, composer.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tracks);
    }
}
