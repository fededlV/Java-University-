package org.fede.services;

import org.fede.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ParseoArchivo {
    private List<Juego> juegosEnMemoria = new ArrayList<>();
    private Map<String, ClasificacionESRB> clasificacionESRBMap = new HashMap<>();

    public ParseoArchivo() {
        inicializarClasificaciones();
    }

    public void cargarJuegosDesdeCSV(Path rutaArchivo) throws IOException, ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        try(BufferedReader br = new BufferedReader(new FileReader(String.valueOf(rutaArchivo)))) {
            String linea = br.readLine();

            while((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                //validar longitud minima del arreglo
                if(partes.length < 9) {
                    System.out.println("Linea invalida" + Arrays.toString(partes));
                    continue;
                }

                //Validar los campos esenciales
                if(partes[2].equals("[]") || partes[4].equals("[]")) {
                    continue; //saltar si no hay desarrollador o plataforma
                }

                //Parsear cada campo del csv
                String title = partes[0];
                LocalDate releaseDate = parsearFecha(partes[1], formatoFecha);
                List<Desarrollador> desarrolladores = parsearDesarrolladores(partes[2]);
                String resumen = partes[3];
                List<Plataforma> plataformas = parsearPlataformas(partes[4]);
                List<String> generos = parsearGeneros(partes[5]);
                Float rating = parsearRanting(partes[6]);
                Integer plays = parsearPlays(partes[7]);
                ClasificacionESRB clasificacionESRB = clasificacionESRBMap.getOrDefault(partes[8], clasificacionESRBMap.get("UR"));

                //Crear juego
                Juego juego = new Juego(title, releaseDate, rating, resumen, plays, desarrolladores, generos, plataformas, clasificacionESRB);
                juegosEnMemoria.add(juego);
            }
        }
    }
            //Metodos auxiliares para parseo y creacion de instancias

    private LocalDate parsearFecha(String fechaStr, SimpleDateFormat formatoFecha) throws ParseException {
        if (fechaStr.isEmpty() || fechaStr.equals("TBD")) return null;
        return formatoFecha.parse(fechaStr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private List<Desarrollador> parsearDesarrolladores(String desarrolladoresStr) {
        return parsearEntidad(desarrolladoresStr, "Desarrollador");
    }

    private List<Plataforma> parsearPlataformas(String plataformasStr) {
        return parsearEntidad(plataformasStr, "Plataforma");
    }

    private List<String> parsearGeneros(String generosStr) {
        if(generosStr.equals("[]")) return Collections.emptyList();
        return Arrays.asList(generosStr.substring(1, generosStr.length() - 1).split(","));
    }

    private Float parsearRanting(String ratingStr) {
        if (ratingStr.equals("N/A")) return null;
        return Float.parseFloat(ratingStr);
    }

    private Integer parsearPlays(String playsStr) {
        if (playsStr.contains("K")) {
            return Integer.parseInt(playsStr.replace("K", "")) * 1000;
        }
        return Integer.parseInt(playsStr);
    }

    private <T> List<T> parsearEntidad(String entidadStr, String tipo) {
        if(entidadStr.equals("[]")) {
            return Collections.emptyList(); //Lista vacia si no hay valores
        }

        String valores = entidadStr.substring(1, entidadStr.length() - 1); // Remover corchetes
        String[] elementos = valores.split(",");

        if(elementos.length == 1) {
            //caso de una sola entidad
            if(tipo.equals("Desarrollador")) {
                return List.of((T) new Desarrollador(elementos[0].trim(), false, null));
            } else if(tipo.equals("Plataforma")) {
                return List.of((T) new Plataforma(elementos[0].trim(), false, null));
            }
        }

        StringBuilder detalle = new StringBuilder();
        StringBuilder sigla = new StringBuilder();

        for (String elemento : elementos) {
            String limpio = elemento.trim();
            detalle.append(limpio).append(", ");
            sigla.append(limpio.charAt(0)); //Usar la inicial.
        }

        //Crear la entidad combinada
        String detalleFinal = detalle.substring(0, detalle.length() - 2); //Quitar la ultima coma
        String siglaFinal = sigla.toString();

        if(tipo.equals("Desarrollador")) {
            return List.of((T) new Desarrollador(siglaFinal, true, detalleFinal));
        } else if(tipo.equals("Plataforma")) {
            return List.of((T) new Plataforma(siglaFinal, true, detalleFinal));
        }
        return Collections.emptyList();
    }

    private void inicializarClasificaciones() {
        clasificacionESRBMap.put("E", new ClasificacionESRB("E", "Apto para todas las edades"));
        clasificacionESRBMap.put("E10+", new ClasificacionESRB("E10+", "Apto para mayores de 10 años"));
        clasificacionESRBMap.put("T", new ClasificacionESRB("T", "Apto para mayores de 13 años"));
        clasificacionESRBMap.put("M", new ClasificacionESRB("M", "Apto para mayores de 17 años"));
        clasificacionESRBMap.put("AO", new ClasificacionESRB("AO", "Apto solo para adultos mayores de 18 años"));
        clasificacionESRBMap.put("RP", new ClasificacionESRB("RP", "Clasificación pendiente"));
        clasificacionESRBMap.put("UR", new ClasificacionESRB("UR", "Clasificación indeterminada"));
    }

    public List<Juego> getJuegosEnMemoria() {
        return juegosEnMemoria;
    }

}
