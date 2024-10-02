package org.example.infraestructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.domain.model.Repository;
import org.example.domain.repository.RepositoryRepository;

import java.util.List;
import java.util.Set;

public class H2Repository implements RepositoryRepository {
    private static final  String PERSISTENCE_UNIT_NAME = "repository-db";

    @Override
    public void saveAll(Set<Repository> repositories) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            repositories.forEach(entityManager::persist);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Repository> findByUser(String user) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            return entityManager.createQuery("SELECT r FROM Repository r WHERE r.user.name = :user", Repository.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Repository> findByLanguage(String language) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            return entityManager.createQuery("SELECT r FROM Repository r, IN(r.languages) l WHERE l.name = :language", Repository.class)
                    .setParameter("language", language)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Repository> findByTag(String tag) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            return entityManager.createQuery("SELECT r FROM Repository r, IN(r.tags) t WHERE t.name = :tag", Repository.class)
                    .setParameter("tag", tag)
                    .getResultList();
        } catch (Exception e ) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
