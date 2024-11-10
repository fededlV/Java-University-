package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.model.Track;

import java.util.Collection;

public class TrackRepository {
    private EntityManager entityManager;

    public TrackRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Track trackToBeSaved) {
        begin();
        entityManager.persist(trackToBeSaved);
        commit();
    }

    public void saveAll(Collection<Track> toBeSaved) {
        toBeSaved
                .forEach(this::save);
    }


    private void begin() {
        entityManager.getTransaction().begin();
    }

    private void commit() {
        entityManager.getTransaction().commit();
    }

}
