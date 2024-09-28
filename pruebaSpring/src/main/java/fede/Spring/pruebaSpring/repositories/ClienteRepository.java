package fede.Spring.pruebaSpring.repositories;

import fede.Spring.pruebaSpring.model.Cliente;

public interface ClienteRepository {
    Cliente findById(int id);
}
