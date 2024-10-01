package org.example.domain.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {
    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "total_miliseconds")
    private int totalMiliseconds;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    private List<Track> tracks;

    public Album(String name) {
        this.name = name;
    }
    public Album() {}

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Album o) {
        return this.name.compareTo(o.name);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        if (tracks == null) {
            tracks = new ArrayList<>();
        }
        tracks.add(track);
    }

    public void setTotalMiliseconds() {
        this.totalMiliseconds = (int) tracks
                .stream()
                .mapToLong(Track::getMilliseconds)
                .sum();
    }

    public String getTotalTime() {
        setTotalMiliseconds();
        int total = totalMiliseconds;
        long hours = total / 3600000;
        long minutes = (total % 3600000) / 60000;
        long seconds = (total % 3600000) / 1000;
        long milliseconds = total % 1000;
        return String.format("%dhs:%02dm:%02ds:%03dms", hours, minutes, seconds);
    }

    public String getAlbumInfo() {
        String stringTemplate = "- %s tiene un total de %d tracks culminando en una duracion de %s";
        return String.format(stringTemplate, name, tracks.size(), getTotalTime());
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalMiliseconds=" + totalMiliseconds +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id && totalMiliseconds == album.totalMiliseconds && Objects.equals(name, album.name) && Objects.equals(tracks, album.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalMiliseconds, tracks);
    }
}
