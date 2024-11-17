package org.fede.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String titulo;
    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;
    private float rating;
    @Column(name = "juegos_finalizados")
    private Integer juegosFinalizados;
    private String resumen;

    @ManyToMany
    @JoinTable(
            name = "juego_desarrollador",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "desarrollador_id")
    )
    private List<Desarrollador> desarrolladores;

    @ElementCollection
    @CollectionTable(name = "juego_generos",
            joinColumns = @JoinColumn(name = "juego_id"))
    @Column(name = "genero")
    private List<String> generos;

    @ManyToMany
    @JoinTable(
            name = "juego_plataforma",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id")
    )
    private List<Plataforma> plataformas;

    @ManyToOne
    @JoinColumn(name = "clasificacion_esrb_id")
    private ClasificacionESRB clasificacionESRB;

    public Juego() {
    }

    public Juego(String titulo, LocalDate fechaLanzamiento, float rating, String resumen, Integer juegosFinalizados,  List<Desarrollador> desarrolladores, List<String> generos, List<Plataforma> plataformas, ClasificacionESRB clasificacionESRB) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.rating = rating;
        this.resumen = resumen;
        this.juegosFinalizados = juegosFinalizados;
        this.desarrolladores = desarrolladores;
        this.generos = generos;
        this.plataformas = plataformas;
        this.clasificacionESRB = clasificacionESRB;
    }

    public Integer getId() {
        return Id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Integer getJuegosFinalizados() {
        return juegosFinalizados;
    }

    public void setJuegosFinalizados(Integer juegosFinalizados) {
        this.juegosFinalizados = juegosFinalizados;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public List<Desarrollador> getDesarrolladores() {
        return desarrolladores;
    }

    public void setDesarrolladores(List<Desarrollador> desarrolladores) {
        this.desarrolladores = desarrolladores;
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public ClasificacionESRB getClasificacionESRB() {
        return clasificacionESRB;
    }

    public void setClasificacionESRB(ClasificacionESRB clasificacionESRB) {
        this.clasificacionESRB = clasificacionESRB;
    }
}
