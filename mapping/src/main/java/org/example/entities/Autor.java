package org.example.entities;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "nombre", unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "autores")
    private Set<Libro> libros = new HashSet<>();
}
