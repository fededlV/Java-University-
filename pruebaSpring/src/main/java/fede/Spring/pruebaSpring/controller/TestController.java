package fede.Spring.pruebaSpring.controller;

import fede.Spring.pruebaSpring.model.Cliente;
import fede.Spring.pruebaSpring.service.ClienteService;
import fede.Spring.pruebaSpring.service.ClienteServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class TestController {

    //Construye instancia de servicio
    //Inyectalo

    private ClienteService service;

    @Autowired
    public TestController(ClienteService s) {
        this.service = s;
    }

    @GetMapping("/{id}")
    public Cliente saludar(@PathVariable int id) {
        return service.findById(id);
    }
}
