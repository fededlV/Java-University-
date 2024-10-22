package org.fede.springjpa.controllers;

import org.fede.springjpa.dto.SchoolDto;
import org.fede.springjpa.models.School;
import org.fede.springjpa.repositories.SchoolRepository;
import org.fede.springjpa.services.SchoolServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private final SchoolServices services;

    public SchoolController(SchoolServices services) {
        this.services = services;
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto dto
    ) {
        return services.create(dto);
    }

    @GetMapping("/schools")
    private List<SchoolDto> findAll() {
        return services.findAll();
    }
}
