package org.example;

import org.example.domain.model.Genre;
import org.example.domain.model.Track;
import org.example.domain.services.TrackService;
import org.example.infraestructure.JPARepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        TrackService trackService = new TrackService(new JPARepository());

        //Requerimiento 1.
        Set<Track> tracks = trackService.loadTracks();

        //Requerimiento 2
        System.out.println("Cantidad de tracks importados: " + tracks.size());
        Long totalMilliseconds = tracks.stream()
                .mapToLong(track -> track.getMilliseconds())
                .sum();

        long seconds = totalMilliseconds / 1000;
        long restMilliseconds = totalMilliseconds % 1000;
        long minutes = seconds / 60;
        long restSeconds = seconds & 60;
        long hours = minutes / 60;
        long restMinutes = minutes % 60;
        System.out.println("Duracion total de la lista: ");
        System.out.print("Horas: " + hours + " ");
        System.out.print("Minutos: " + restMinutes + " ");
        System.out.print("Segundos: " + restSeconds + " ");
        System.out.print("Milisegundos: " + restMilliseconds + " ");


        //Requerimiento 3
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nombre de albumes | Cantida de tracks | Duracion de album");
        stringBuilder.append("\n");
        tracks
                .stream()
                .map(Track::getAlbum)
                .distinct()
                .sorted()
                .forEach((album) ->{
                    long cantTracks = album.getTracks().size();
                    stringBuilder.append(String.format("%s | %s | %s", album.getName(), cantTracks, album.getTotalTime()))
                            .append("\n");
                });
        System.out.println(stringBuilder);

        //Requerimiento 5
        trackService.saveAll(tracks);

        //Consulta Base de datos
        //Consulta de todos los tracks
        List<Track> tracks1 = trackService.findAll();

        //Consulta por Album
        List<Track> tracks2 = trackService.findByAlbum("Acústico");

        //Consulta por Genero
        List<Track> tracks3 = trackService.findByGenre("Rock");

        //Consulta por artista
        List<Track> tracks4 = trackService.findByArtist("Angus Young");
    }
}