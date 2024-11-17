package org.fede;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.fede.entities.Desarrollador;
import org.fede.entities.Juego;
import org.fede.entities.Plataforma;
import org.fede.services.ParseoArchivo;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Configurar EntityManager para persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("parcial-pu");
        EntityManager em = emf.createEntityManager();

        //Instancia del cargador de juegos
        ParseoArchivo parseoArchivo = new ParseoArchivo();

        try {
            //Paso 1. Cargar archivo CSV en memoria
            Path path = Paths.get("C:\\Mis Archivos\\Cursos Programacion\\Java-University-\\Parcial\\practica1\\games_data.csv");
            parseoArchivo.cargarJuegosDesdeCSV(path);
            List<Juego> juegos = parseoArchivo.getJuegosEnMemoria();
            System.out.println("Total de juegos cargados en memoria: " + juegos.size());

            //Paso 2. Determinar cantidad de juegos por plataforma
            System.out.println("\nCantidad de juegos por plataforma (exclusivos): ");
            Map<String, Long> juegosPorPlataforma = juegos.stream()
                    .filter(juego -> juego.getPlataformas().size() == 1) //Solo juegos exclusivos
                    .collect(Collectors.groupingBy(
                            juego -> juego.getPlataformas().get(0).getNombre(),
                            Collectors.counting()
                    ));
            juegosPorPlataforma.entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) //Ordenar de mayor a menor
                    . forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            //Paso 3. Determinar cantidad de juegos por desarrollador
            System.out.println("\nCantidad de juegos por desarrollador (mas de 30 juegos): ");
            Map<String, Long> juegosPorDesarrollador = juegos.stream()
                    .filter(juego -> juego.getDesarrolladores().size() == 1)
                    .collect(Collectors.groupingBy(
                            juego -> juego.getDesarrolladores().get(0).getNombre(),
                            Collectors.counting()
                    ));
            juegosPorDesarrollador.entrySet().stream()
                    .filter(entry -> entry.getValue() > 30) //Solo desarrolladores con mas de 30 juegos
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) //Ordenar de mayor a menor
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            //Total de juegos con mas de un desarrollador
            long juegosMultiDesarrollador = juegos.stream()
                    .filter(juego -> juego.getDesarrolladores().size() > 1)
                    .count();
            System.out.println("Total de juegos con mas de un desarrollador: " + juegosMultiDesarrollador);

            //Paso 4. Determinar mejor desarrollador
            System.out.println("\nMejor desarrolldor por rating promedio (juegos exclusivos):");
            Map<String, Double> ratingPorDesarrollador = juegos.stream()
                    .filter(juego -> juego.getDesarrolladores().size() == 1 && juego.getClasificacionESRB() != null) //Exclusivos con rating
                    .collect(Collectors.groupingBy(
                            juego -> juego.getDesarrolladores().get(0).getNombre(),
                            Collectors.averagingDouble(Juego::getRating)
                    ));
            ratingPorDesarrollador.entrySet().stream()
                    .max(Map.Entry.comparingByValue()) //Obtener el desarollador con mayor rating promedio
                    .ifPresent(entry -> System.out.println(entry.getKey() + " con rating promedio de: " + entry.getValue()));

            //Paso 5. Popular la base de datos
            em.getTransaction().begin();
            for (Juego juego : juegos) {
                //Persistir desarrolladores y plataformas si no existen
                for (Desarrollador desarrollador : juego.getDesarrolladores()) {
                    if(em.find(Desarrollador.class, desarrollador.getId()) == null) {
                        em.persist(desarrollador);
                    }
                }
                for (Plataforma plataforma : juego.getPlataformas()) {
                    if(em.find(Plataforma.class, plataforma.getId()) == null) {
                        em.persist(plataforma);
                    }
                }
                //Persistir el juego y su clasificacion
                em.persist(juego);
            }
            em.getTransaction().commit();
            System.out.println("\nDatos persistidos en la base de datos.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}