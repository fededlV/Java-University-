package org.fede;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.fede.entities.ServicioMascotas;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Mis Archivos\\Cursos Programacion\\Java-University-\\Parcial\\practica/mascotas.csv");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persi");
        EntityManager em = emf.createEntityManager();
        ServicioMascotas servicioMascotas = new ServicioMascotas(em);

        servicioMascotas.cargarMascotasDesdeCSV(String.valueOf(path));

        //guardar mascotas vivas
        servicioMascotas.guardarVivas();

        //obtener mascotas felices
        System.out.println("Mascotas felices ");
        servicioMascotas.getFelices().forEach(System.out::println);

        em.close();
        emf.close();

    }
}