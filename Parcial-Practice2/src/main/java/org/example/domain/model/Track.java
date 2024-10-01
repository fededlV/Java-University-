package org.example.domain.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "track_id")
    private int id;
    private String name;
    private long milliseconds;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id", referencedColumnName = "album_id")
    private Album album;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_type_id")
    private MediaType mediaType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "track_artist",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "track_composer",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "composer_id")
    )
    private Set<Composer> composers;

    public Track() {}

    public Track(String name, long milliseconds, Album album, Genre genre, MediaType mediaType, Set<Artist> artists, Set<Composer> composers) {
        this.name = name;
        this.milliseconds = milliseconds;
        this.album = album;
        this.genre = genre;
        this.mediaType = mediaType;
        this.artists = artists;
        this.composers = composers;
        setTrackToArtists();
        setTrackToComposers();
    }

    private void setTrackToArtists() {
        artists.forEach(artist -> artist.addTracks(this));
    }

    private void setTrackToComposers() {
        composers.forEach(composer -> composer.addTrack(this));
    }

    public void getDuration() {
        long minutes = milliseconds / 60000;
        long seconds = (milliseconds % 60000) / 1000;
        long milliseconds = this.milliseconds % 1000;
        System.out.println(String.format("%d:%02d:%03d", minutes, seconds, milliseconds));
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

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Composer> getComposers() {
        return composers;
    }

    public void setComposers(Set<Composer> composers) {
        this.composers = composers;
    }

    @Override
    public String toString() {
        String stringTemplate = "Track { id = %d , name = %s, duration = %d%n %s %n %s %n artists = %s %n composers = %s %n}";
        String artistsString = artists.stream()
                .map(Artist::toString)
                .collect(Collectors.joining(" , "));
        String composersString = composers.stream()
                .map(Composer::toString)
                .collect(Collectors.joining(" , "));
        return String.format(stringTemplate, id, name, milliseconds, album, genre, mediaType, artistsString, composersString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id && milliseconds == track.milliseconds && Objects.equals(name, track.name) && Objects.equals(album, track.album) && Objects.equals(genre, track.genre) && Objects.equals(mediaType, track.mediaType) && Objects.equals(artists, track.artists) && Objects.equals(composers, track.composers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, milliseconds, album, genre, mediaType, artists, composers);
    }
}