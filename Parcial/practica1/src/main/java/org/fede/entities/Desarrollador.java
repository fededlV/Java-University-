package org.fede.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Desarrolladores")
public class Desarrollador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nombre;
    private boolean multivalor;
    private String detalle;


    public Desarrollador() {
    }

    public Desarrollador(String nombre, boolean multivalor, String detalle) {
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
        return "Desarrollador{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", multivalor=" + multivalor +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
