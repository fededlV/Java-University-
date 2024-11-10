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
    private int id;
    private String name;

    @ManyToMany(mappedBy = "composers")
    private Set<Track> tracks;

    public Composer() {}

    public Composer(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        if(tracks == null) {
            tracks = new HashSet<>();
        }
        tracks.add(track);
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
        return id == composer.id && Objects.equals(name, composer.name) && Objects.equals(tracks, composer.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tracks);
    }
}
