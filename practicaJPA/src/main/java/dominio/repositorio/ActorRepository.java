package dominio.repositorio;

import dominio.modelo.Actor;

import java.util.List;

public interface ActorRepository {

    List<Actor> findAll();

}
