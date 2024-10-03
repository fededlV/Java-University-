package org.example.infraestructure;

import jakarta.persistence.*;
import org.example.domain.model.Track;
import org.example.domain.model.TrackRepository;

import java.util.List;

public class JPARepository implements TrackRepository {

    @Override
    public void saveAll(List<Track> tracks) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence")){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            tracks.forEach(entityManager::persist);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Track> findAll() {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            return  entityManager.createQuery("SELECT t FROM Track t", Track.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Track> findByAlbum(String album) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            return entityManager.createQuery("SELECT t FROM Track t WHERE t.album.name = :album", Track.class)
                    .setParameter("album", album)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Track> findByGenre(String genre) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            return entityManager.createQuery("SELECT t FROM Track t WHERE t.genre.name = :genre", Track.class)
                    .setParameter("genre", genre)
                    .getResultList();
        }
    }
}
