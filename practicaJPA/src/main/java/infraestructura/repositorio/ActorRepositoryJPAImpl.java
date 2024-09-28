package infraestructura.repositorio;

import dominio.modelo.Actor;
import dominio.repositorio.ActorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ActorRepositoryJPAImpl
        implements ActorRepository {

    EntityManagerFactory emf;

    public ActorRepositoryJPAImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Actor> findAll() {
        try (EntityManager em = this.emf.createEntityManager();) {
            return em
                    .createNamedQuery("Actor.findAll")
                    .getResultList();
        }
    }
}
