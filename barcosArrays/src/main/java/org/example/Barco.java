package org.example;

public class Barco {
    private String matricula;
    private int nroMuelle;
    private int capacidadCarga;
    private int costoAlquiler;
    private Capitan capitan;

    public Barco(String matricula, Capitan capitan, int costoAlquiler, int capacidadCarga, int nroMuelle) {
        this.matricula = matricula;
        this.capitan = capitan;
        this.costoAlquiler = costoAlquiler;
        this.capacidadCarga = capacidadCarga;
        this.nroMuelle = nroMuelle;
    }
}
