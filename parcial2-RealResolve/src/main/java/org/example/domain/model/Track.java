package org.example.domain.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Integer id;
    private String name;
    private Integer milliseconds;

    @ManyToMany
    @JoinTable(name = "TRACK_ARTIST",
     joinColumns =
        @JoinColumn(name = "track_id", referencedColumnName = "track_id"),
     inverseJoinColumns =
        @JoinColumn (name = "artist_id", referencedColumnName = "artist_id")
    )
    private Set<Artist> artists;

    @ManyToMany
    @JoinTable(name = "track_composer",
    joinColumns = @JoinColumn(name = "track_id", referencedColumnName = "track_id"),
    inverseJoinColumns = @JoinColumn(name = "composer_id", referencedColumnName = "composer_id")
    )
    private Set<Composer> composers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_type_id")
    private MediaType mediaType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private Album album;

    public Track() {}

    public Track(Integer id, String name, Integer milliseconds, Genre genre, Set<Artist> artists, Set<Composer> composers, Album album, MediaType mediaType) {
        this.id = id;
        this.name = name;
        this.milliseconds = milliseconds;
        this.genre = genre;
        this.artists = artists;
        this.composers = composers;
        this.album = album;
        this.mediaType = mediaType;
        album.addTrack(this);
    }

    /*private void setTrackToArtist() {
     artists.forEach(artist -> artist.addTrack(this));
    }

    private void setTrackToComposer() {
     composers.forEach(composer -> composer.addTrack(this));
    }*/

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

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public void addArtist(Artist artist) {
        if(artists == null) {
            artists = new HashSet<>();
        }
        artists.add(artist);
    }

    public Integer getMilliseconds() {
        return milliseconds;
    }

    public void getDuration() {
        long min = milliseconds / 60000;
        long sec = (milliseconds % 60000) / 1000;
        long mill = this.milliseconds % 1000;
        System.out.println(String.format("%d:%02d.%03d", min, sec, mill));
    }

    public void setMilliseconds(Integer milliseconds) {
        this.milliseconds = milliseconds;
    }

    public Set<Composer> getComposers() {
        return composers;
    }

    public void setComposers(Set<Composer> composers) {
        this.composers = composers;
    }

    public void addComposers(Composer composer) {
        if(composers.isEmpty()) {
            composers = new HashSet<>();
        }
        composers.add(composer);
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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

        return String.format(stringTemplate, id, name, milliseconds, album, genre, mediaType, artistsString,composersString);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id) && Objects.equals(name, track.name) && Objects.equals(milliseconds, track.milliseconds) && Objects.equals(genre, track.genre) && Objects.equals(mediaType, track.mediaType) && Objects.equals(album, track.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, milliseconds, genre, mediaType, album);
    }
}
