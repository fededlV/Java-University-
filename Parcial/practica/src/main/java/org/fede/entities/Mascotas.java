package org.fede.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Mascotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Column(name = "nivel_energia")
    private int nivelEnergia;
    @Column(name = "nivel_humor")
    private int nivelHumor;
    @ManyToMany
    @JoinTable(
            name = "mascota_habilidad",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "habilidad_id")
    )
    private List<Habilidades> habilidades;
    @OneToMany(mappedBy = "mascotas")
    private List<HistoriaMascota> historiaMascotas;

    public Mascotas(String nombre, int nivelEnergia, int nivelHumor, List<Habilidades> habilidades) {
        this.nombre = nombre;
        this.nivelEnergia = nivelEnergia;
        this.nivelHumor = nivelHumor;
        this.habilidades = habilidades;
        this.historiaMascotas = new ArrayList<>();
    }

    public Mascotas() {}

    //Metodo para ingerir alimento
    public void ingerir() {
        verificarEstado();
        int cantIngestas = 0;
        int nivelEnergiaFin1 = 0;
        int nivelEnergiaFin2 = 0;

        //Logica de ingesta
        if(cantIngestas < 3) {
        nivelEnergiaFin1 = nivelEnergia += 10;
        nivelHumor += 1;
        cantIngestas++;
            historiaMascotas.add(new HistoriaMascota("ingerir", this.nivelEnergia, nivelEnergiaFin1, this.nivelHumor, this.nivelHumor + 1, LocalDateTime.now(), this));
        } else {
            nivelEnergiaFin2 = nivelEnergia += 5;
            historiaMascotas.add(new HistoriaMascota("ingerir", this.nivelEnergia, nivelEnergiaFin2, this.nivelHumor, this.nivelHumor, LocalDateTime.now(), this));
        }
        if(cantIngestas >= 5) {
            nivelEnergia = 0;
        }
    }

    //Metodo para ejercitar
    public void ejercitar() {
        verificarEstado();

        //Logica ejercicio
        nivelEnergia -= 10;
        historiaMascotas.add(new HistoriaMascota("ejercitar", this.nivelEnergia, this.nivelEnergia - 10, this.nivelHumor, this.nivelHumor, LocalDateTime.now(), this));
    }

    //Verificar si la mascota esta viva
    private void verificarEstado() {
        if(!estaViva()) {
            throw new MascotaMuertaException("la mascota " + nombre + " esta muerta");
        }
    }

    //1. Metodo getEstadoFisico
    public float getEstadoFisico() {
        long cantidadIngestas = historiaMascotas.stream()
                .filter(a -> a.getTipoHistorico().equals("ingerir"))
                .count();
        long cantidadEjercitaciones = historiaMascotas.stream()
                .filter(a -> a.getTipoHistorico().equals("ejercitar"))
                .count();

        if (cantidadIngestas > 0) {
            return (float) cantidadEjercitaciones / cantidadIngestas;
        } else {
            return 0;
        }
    }

    //2. metodo estaViva
    public boolean estaViva() {
        return nivelEnergia > 0;
    }

    //3. Metodo getRachaFitness
    public int getRachaFitness() {
        int rachaActual = 0;
        int rachaMaxima = 0;

        for (HistoriaMascota accion : historiaMascotas) {
            if (accion.getTipoHistorico().equals("ejercictar")) {
                rachaActual++;
                rachaMaxima = Math.max(rachaMaxima, rachaActual);
            } else {
                rachaActual = 0;
            }
        }
        return rachaMaxima;
    }

    //4. Metodo demostrarHabilidad
    public void demostrarHabilidad(String habilidad) {
        if(habilidades.stream().anyMatch(h -> h.getNombre_habilidad().equalsIgnoreCase(habilidad))) {
            System.out.println("¡Mira como puedo " + habilidad + "!");
            nivelHumor += 1;
            historiaMascotas.add(new HistoriaMascota("demostrar habilidad", this.nivelEnergia, this.nivelEnergia, this.nivelHumor, this.nivelHumor + 1, LocalDateTime.now(), this));
        } else {
            throw new IllegalArgumentException("la mascota no tiene la habilidad: " + habilidad);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelEnergia() {
        return nivelEnergia;
    }

    public void setNivelEnergia(int nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    public int getNivelHumor() {
        return nivelHumor;
    }

    public void setNivelHumor(int nivelHumor) {
        this.nivelHumor = nivelHumor;
    }

    public List<Habilidades> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidades> habilidades) {
        this.habilidades = habilidades;
    }

    public List<HistoriaMascota> getHistoriaMascotas() {
        return historiaMascotas;
    }

    public void setHistoriaMascotas(List<HistoriaMascota> historiaMascotas) {
        this.historiaMascotas = historiaMascotas;
    }

    @Override
    public String toString() {
        return "Mascotas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivelEnergia=" + nivelEnergia +
                ", nivelHumor=" + nivelHumor +
                ", habilidades=" + habilidades +
                ", historiaMascotas=" + historiaMascotas +
                '}';
    }
}
