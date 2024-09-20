package org.example;


import jakarta.persistence.*;
import org.example.entity.Actor;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila-pu");
        EntityManager em = emf.createEntityManager();
        //Query a nivel de objeto.
        em.createQuery("SELECT a FROM Actor a", Actor.class).getResultStream()
                .forEach(System.out::println); //Esta opcion es para mostrar la lista utilizando streams.

        System.out.println("\nEstos son actores con id > 20");
        em.createQuery("SELECT a " +
                "FROM Actor a " +
                "WHERE a.id > 20",
                Actor.class).getResultStream().forEach(System.out::println);

        //Para poder pasarle parametros para la busqueda
        System.out.println("\nEstos son actores con id > 20");
        TypedQuery<Actor> q = em.createQuery("SELECT a " + //En vez de typedquery puede ser var
                        "FROM Actor a " +
                        "WHERE a.id > :limit",
                Actor.class);
        q.setParameter("limit", 20);
        List<Actor> actors = q.getResultList();
        for (Actor a : actors) {
            System.out.println(a);
        }

        //Llamar una query q se establece en la clase actor.
        System.out.println("\nEsta query es nativa del actor");
        em.createNamedQuery("Actor.findAll").getResultStream().forEach(System.out::println);

        //Ejecutar query
        // 1 forma (List<Actor> l = q.getResultList();)

        //Iterar lista para mostrar
        /*for(Actor a : q) {
            System.out.println(a);
        }*/

        //Insert de un actor con hibernate
        /*Actor nuevo = new Actor();
        nuevo.setFirstName("Michael");
        nuevo.setLastName("Jackson");
        nuevo.setLastUpdate(LocalDateTime.now());

        System.out.println("Añadiendo nuevo actor..");
        em.getTransaction().begin();
        em.persist(nuevo);
        em.getTransaction().commit();
        System.out.println("Finalizado.");*/

        em.close();
        emf.close();

    }
}