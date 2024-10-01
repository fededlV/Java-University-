package org.example;

import org.example.domain.model.Track;
import org.example.domain.service.TrackService;
import org.example.infraestructure.H2Track;
import org.example.repository.TrackRepository;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        TrackService service = new TrackService(new H2Track());

        Set<Track> tracks = service.loadTracks();

        for(Track track : tracks) {
            System.out.println(track);
        }
    }
}