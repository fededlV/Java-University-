package org.example.domain.service;

import org.example.domain.model.*;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
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
        List<Track> tracks = new ArrayList<>();

        try(Stream<String> stream = Files.lines(
                Path.of(
                        ClassLoader
                                .getSystemResource("Tracks_Data.txt")
                                .toURI()))) {

            //Con skip ignoramos la primer linea que contine los nombres de las columnas
            return stream.skip(1)
                    .map((line) -> mapTrack(line, artists, albums, composers, genres, mediaTypes))
                    .collect(Collectors.toSet());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Track mapTrack(String line,
                           Map<String, Artist> processedArtits,
                           Map<String, Album> processedAlbums,
                           Map<String, Composer> processedComposers,
                           Map<String, Genre> processedGenres,
                           Map<String, MediaType> processedMediaTypes) {
        String [] values = line.split("\\|");

        Integer id = Integer.parseInt(values[0]);
        String nameTrack = values[1];
        String albumName = values[2];
        Set<Artist> artists = parseArtist(values[3], processedArtits);
        Set<Composer> composers = parseComposer(values[4], processedComposers);
        Long milliseconds = Long.parseLong(values[5]);
        String genreName = values[6];
        String mediaTypesName = values[7];

        Genre genre = processedGenres.computeIfAbsent(genreName, Genre::new);
        MediaType mediaType = processedMediaTypes.computeIfAbsent(mediaTypesName, MediaType::new);
        Album album = processedAlbums.computeIfAbsent(albumName, Album::new);

        Track track = new Track(
                id,
                nameTrack,
                milliseconds,
                album,
                genre,
                mediaType,
                artists,
                composers
        );

        //Save Genre, album and mediaType after create to Track
        processedAlbums.put(albumName, album);
        processedGenres.put(genreName, genre);
        processedMediaTypes.put(mediaTypesName, mediaType);
        /*artists.forEach((artist) -> processedArtits.put(artist.getName(), artist));*/
        /*composers.forEach((composer) -> processedComposers.put(composer.getName(), composer));*/

        //asociate with track
        composers.forEach(composer -> composer.addTrack(track));
        artists.forEach(artist -> artist.addTracks(track));
        album.addTrack(track);

        return track;
    }

    private <T> Set<T> parseValues(String values,
                                   Map<String, T> processedItems,
                                   Function<String, T> createNewItem) {
        return Arrays.stream(values.split(","))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .map(itemName -> processedItems.computeIfAbsent(itemName, createNewItem))
                .collect(Collectors.toSet());
    }

    private Set<Artist> parseArtist(String values, Map<String, Artist> artists) {
        return parseValues(values, artists, Artist::new);
    }

    private Set<Composer> parseComposer(String values, Map<String,Composer> composers) {
        return parseValues(values, composers, Composer::new);
    }

    //Show for screen list of albums sort.
   /* public void showAlbums() {
        List<Album> albumsOrdenados = new ArrayList<>(albums.values);
    }*/
}
