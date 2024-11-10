package org.fede.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Habilidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre_habilidad;
    @ManyToMany(mappedBy = "habilidades")
    private List<Mascotas> mascotas;

    public String getNombre_habilidad() {
        return nombre_habilidad;
    }

    public void setNombre_habilidad(String nombre_habilidad) {
        this.nombre_habilidad = nombre_habilidad;
    }

    public List<Mascotas> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascotas> mascotas) {
        this.mascotas = mascotas;
    }

    public Habilidades() {
    }

    public Habilidades(String nombre_habilidad, List<Mascotas> mascotas) {
        this.nombre_habilidad = nombre_habilidad;
        this.mascotas = mascotas;
    }

    @Override
    public String toString() {
        return "Habilidades{" +
                "id=" + id +
                ", nombre_habilidad='" + nombre_habilidad + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habilidades that = (Habilidades) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre_habilidad, that.nombre_habilidad) && Objects.equals(mascotas, that.mascotas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre_habilidad, mascotas);
    }
}
