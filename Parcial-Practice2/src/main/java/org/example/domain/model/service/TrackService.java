package org.example.domain.model.service;

import org.example.domain.model.*;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class TrackService {

    private TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void saveAll(Set<Track> tracks) {
        trackRepository.saveAll(tracks);
    }

    public List<Track> findByAlbum(String albumName) {
        return trackRepository.findByAlbum(albumName);
    }

    public List<Track> findByArtist(String artistName) {
        return trackRepository.findByArtist(artistName);
    }

    public List<Track> findByGenre(String genreName) {
        return trackRepository.findByGenre(genreName);
    }

    public List<Track> findByName(String trackName) {
        return trackRepository.findByName(trackName);
    }

    public Set<Track> loadTracks() {
        //Para Mantener una instancia de genre, artist, album, composer, mediaType
        Map<String, Artist> artists = new HashMap<>();
        Map<String, Album> albums = new HashMap<>();
        Map<String, Composer> composers = new HashMap<>();
        Map<String, Genre> genres = new HashMap<>();
        Map<String, MediaType> mediaTypes = new HashMap<>();

        try(Stream<String> stream = Files.lines(
                Path.of(
                        ClassLoader
                                .getSystemResource("Tracks_Data.txt")
                                .toURI()))) {
            //Con skip ignoramos la primer linea que contine los nombres de las columnas
            return stream.skip(1)
                    .map((line) -> mapRepository)
        }
    }
}
