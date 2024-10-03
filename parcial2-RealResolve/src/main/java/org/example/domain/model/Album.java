package org.example.domain.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer id;
    private String name;
    @Column(name = "total_milleconds")
    private Integer totalMilliseconds;

    @OneToMany(mappedBy = "album")
    private Set<Track> tracks;

    public Album(String name) {
        this.name = name;
        this.tracks = new HashSet<>();
    }

    public Album() {}

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

    public void setTotalMilliseconds() {
        this.totalMilliseconds = (int) tracks
                .stream()
                .mapToInt(Track::getMilliseconds)
                .sum();
    }

    public String getTotalMilliseconds() {
        setTotalMilliseconds();
        int total = totalMilliseconds;
        long hours = total / 3600000;
        long minutes = (total %3600000) / 60000;
        long seconds = (total % 60000) / 1000;
        long milliseconds = total % 1000;
        return String.format("%dhs:%02dm:%02ds:%03dms", hours, minutes, seconds, milliseconds);
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public String getAlbumInfo() {
        String stringTemplate = "- %s tiene un total de %d tracks culminando en una duracion de %s";
        return String.format(stringTemplate, name, tracks.size(), getTotalMilliseconds());
    }


    @Override
    public int compareTo(Album o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalTime=" + getTotalMilliseconds() +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) && Objects.equals(name, album.name) && Objects.equals(totalMilliseconds, album.totalMilliseconds) && Objects.equals(tracks, album.tracks);
    }
}
