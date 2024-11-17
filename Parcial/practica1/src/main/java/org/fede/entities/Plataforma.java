package org.fede.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Plataformas")
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nombre;
    private boolean multivalor;
    private String detalle;


    public Plataforma() {
    }

    public Plataforma(String nombre, boolean multivalor, String detalle) {
        this.nombre = nombre;
        this.multivalor = multivalor;
        this.detalle = detalle;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isMultivalor() {
        return multivalor;
    }

    public void setMultivalor(boolean multivalor) {
        this.multivalor = multivalor;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Plataforma{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", multivalor=" + multivalor +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
