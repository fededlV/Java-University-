package fede.Spring.pruebaSpring.repositories;

import fede.Spring.pruebaSpring.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository{

    private Map<Integer, Cliente> clientes;
    public ClienteRepositoryImpl(){
        clientes = new HashMap<>();
        clientes.put(1, new Cliente(1, "Federico"));
        clientes.put(2, new Cliente(2, "Sol"));
        clientes.put(3, new Cliente(3, "Santi"));
    }

    @Override
    public Cliente findById(int id) {
        return clientes.get(id);
    }
}
