package ar.edu.utn.frc.backend;

import java.time.LocalDate;

public class Porcino extends Animal{
    public Porcino(float aPeso, LocalDate aFechaNacimiento, Sexo aSexo, RazaPorcino aRaza) {
        super(aPeso, aFechaNacimiento, aSexo, aRaza.getNombre());
    }

    @Override
    public Racion[] getDieta() {
        float cantAComer = getPeso() * 2.0f;
        float cantidadMaiz = cantAComer * 0.5f;
        float cantidadSoja = cantAComer * 0.3f;
        float cantidadForraje = cantAComer * 0.2f;
        return new Racion[]{
                new Racion(Alimento.MAIZ, cantidadMaiz),
                new Racion(Alimento.SOJA, cantidadSoja),
                new Racion(Alimento.FORRAJE, cantidadForraje)
        };
    }
}
