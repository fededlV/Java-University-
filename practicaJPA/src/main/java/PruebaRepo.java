import dominio.modelo.Actor;
import dominio.repositorio.ActorRepository;
import infraestructura.repositorio.ActorRepositoryJPAImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PruebaRepo {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("sakila-pu");

        ActorRepository repo = new ActorRepositoryJPAImpl(emf);

        List<Actor> l = repo.findAll();

        emf.close();
    }
}
