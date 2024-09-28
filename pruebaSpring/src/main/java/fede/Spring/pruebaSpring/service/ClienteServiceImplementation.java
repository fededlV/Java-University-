package fede.Spring.pruebaSpring.service;

import fede.Spring.pruebaSpring.model.Cliente;
import fede.Spring.pruebaSpring.repositories.ClienteRepository;
import fede.Spring.pruebaSpring.repositories.ClienteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService {

    private ClienteRepository repo;

    @Autowired
    public ClienteServiceImplementation(ClienteRepository r) {
        this.repo = r;
    }

    @Override
    public Cliente findById(int id) {
        return repo.findById(id);
    }
}
