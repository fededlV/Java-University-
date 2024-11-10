package org.example.domain.model;

import java.util.List;
import java.util.Set;

public interface TrackRepository {

    void saveAll(Set<Track> tracks);

    List<Track> findByAlbum(String albumName);

    List<Track> findByGenre(String genreName);

    List<Track> findByArtist(String artistName);

    List<Track> findByName(String trackName);
}
