package org.example.infraestructure;

import org.example.domain.model.Track;
import org.example.domain.model.TrackRepository;

import java.util.List;
import java.util.Set;

public class H2Track implements TrackRepository {
    private static String PERSISTENCE_UNIT_NAME = "peristence";

    public H2Track() {
        super();
    }

    @Override
    public void saveAll(Set<Track> tracks) {

    }

    @Override
    public List<Track> findByAlbum(String albumName) {
        return List.of();
    }

    @Override
    public List<Track> findByGenre(String genreName) {
        return List.of();
    }

    @Override
    public List<Track> findByArtist(String artistName) {
        return List.of();
    }

    @Override
    public List<Track> findByName(String trackName) {
        return List.of();
    }
}
