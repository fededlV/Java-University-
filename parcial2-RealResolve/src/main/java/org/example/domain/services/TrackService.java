package org.example.domain.services;

import org.example.domain.model.*;

import javax.swing.plaf.basic.BasicSliderUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class TrackService {

    private TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void saveAll(Set<Track> tracks) {
        trackRepository.saveAll(tracks);
    }

    public List<Track> findAll() {
        return this.trackRepository.findAll();
    }

    public List<Track> findByAlbum(String album) {
        return this.trackRepository.findByAlbum(album);
    }

    public List<Track> findByGenre(String genre) {
        return this.trackRepository.findByGenre(genre);
    }

    public List<Track> findByArtist(String artist) {
        return this.trackRepository.findByArtist(artist);
    }

    public Set<Track> loadTracks() {
        //Requirement 1: Mantain every object in memory one time.
        Map<String, Artist> artistMap = new HashMap<>();
        Map<String, Composer> composerMap = new HashMap<>();
        Map<String, Genre> genreMap = new HashMap<>();
        Map<String, MediaType> mediaTypeMap = new HashMap<>();
        Map<String, Album> albumMap = new HashMap<>();

        try(Stream<String> lines = Files.lines(
                Path.of(
                        ClassLoader.getSystemResource("Tracks_Data.txt").toURI()
                )
        )) {
            return lines.skip(1)
                    .map((line) -> mapTrack(line, artistMap, composerMap, genreMap, mediaTypeMap, albumMap))
                    .collect(Collectors.toSet());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Track mapTrack(String line,
                           Map<String, Artist> artistMap,
                           Map<String, Composer> composerMap,
                           Map<String, Genre> genreMap,
                           Map<String, MediaType> mediaTypeMap,
                           Map<String, Album> albumMap) {
        String[] values = line.split("\\|");

        Integer trackId = Integer.parseInt(values[0]);
        String nameTrack = values[1];
        String albumName = values[2];
        Set<Artist> artists = parseArtist(values[3], artistMap);
        Set<Composer> composers = parseComposer(values[4], composerMap);
        Integer milliseconds = Integer.parseInt(values[5]);
        String genreName = values[6];
        String mediaTypeName = values[7];

        Genre genre = genreMap.getOrDefault(genreName, new Genre(genreName));
        MediaType mediaType = mediaTypeMap.getOrDefault(mediaTypeName, new MediaType(mediaTypeName));
        Album album = albumMap.getOrDefault(albumName, new Album(albumName));

        Track track = new Track(
                trackId,
                nameTrack,
                milliseconds,
                genre,
                artists,
                composers,
                album,
                mediaType
        );
        genreMap.put(genreName, genre);
        mediaTypeMap.put(mediaTypeName, mediaType);
        albumMap.put(albumName, album);
        return track;
    }

    private <T> Set<T> parseValues(String values,
                                   Map<String, T> item,
                                   Function<String, T> createNewItem) {
        return Arrays.stream(values.split(","))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map((itemName) -> item.computeIfAbsent(itemName, createNewItem))
                .collect(Collectors.toSet());
    }

    private Set<Artist> parseArtist(String artist, Map<String, Artist> artistMap) {
        return parseValues(artist, artistMap, Artist::new);
    }

    private Set<Composer> parseComposer(String composer, Map<String, Composer> composerMap) {
        return parseValues(composer, composerMap, Composer::new);
    }

    public void generateGenreReport(String filePath, Set<Album> genres) {
        File file = new File(filePath);

        try(PrintWriter printWriter = new PrintWriter(file)){
            printWriter.println("Genero, Cantidad de Tracks"); //cabecera.
            genres.forEach(genre -> {
                printWriter.println(format("%s,%s,%s",
                        genre.getName()));
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
