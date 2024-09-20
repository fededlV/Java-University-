package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.InfraestructureRepository.ActorRepositoryJPAImpl;

public class PrueaRepo {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila-pu");
    ActorRepository repo = new ActorRepositoryJPAImpl(emf);

    List<Actor> l = repo.findAll();
    emf.close;
}
