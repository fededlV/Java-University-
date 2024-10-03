package org.example;

import org.example.domain.model.Track;
import org.example.domain.services.TrackService;
import org.example.infraestructure.JPARepository;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        TrackService trackService = new TrackService(new JPARepository());

        //Requerimiento 1.
        Set<Track> tracks = trackService.loadTracks();
        for(Track track : tracks) {
            System.out.println(track);
        }
    }
}