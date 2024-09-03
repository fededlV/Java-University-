package ar.edu.utn.frc.backend;

import java.time.LocalDate;

public class Bovino extends Animal {

    public Bovino(float aPeso, LocalDate aFechaNacimiento, Sexo aSexo, RazaBovino aRaza) {
        super(aPeso, aFechaNacimiento, aSexo, aRaza.getNombre());
    }

    @Override
    public Racion[] getDieta() {
        float cantAComer = getPeso() * 1.5f;
        float cantidadMaiz = cantAComer * 0.4f;
        float cantidadSoja = cantAComer * 0.3f;
        float cantidadForraje = cantAComer * 0.3f;

        return new Racion[]{
                new Racion(Alimento.MAIZ, cantidadMaiz),
                new Racion(Alimento.SOJA, cantidadSoja),
                new Racion(Alimento.FORRAJE, cantidadForraje)
        };
    }
}
