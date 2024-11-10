package org.example.domain.model;

import java.util.List;
import java.util.Set;

public interface TrackRepository {

    void saveAll(Set<Track> tracks);

    List<Track> findAll();

    List<Track> findByAlbum(String album);

    List<Track> findByGenre(String genre);

    List<Track> findByArtist(String artist);
}
