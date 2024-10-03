package org.example.domain.model;

import java.util.List;

public interface TrackRepository {

    void saveAll(List<Track> tracks);

    List<Track> findAll();

    List<Track> findByAlbum(String album);

    List<Track> findByGenre(String genre);
}
