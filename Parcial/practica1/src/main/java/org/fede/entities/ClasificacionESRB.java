package org.fede.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Clasificaciones_ESRB")
public class ClasificacionESRB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String rating;
    private String codigo;



    public ClasificacionESRB() {
    }

    public ClasificacionESRB(String rating, String codigo) {
        this.rating = rating;
        this.codigo = codigo;

    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "ClasificacionESRB{" +
                "Id=" + Id +
                ", rating='" + rating + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
