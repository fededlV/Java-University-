package ar.edu.utn.frc.backend;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Bovino b = new Bovino(300f, LocalDate.now(), Sexo.HEMBRA, RazaBovino.ANGUS);
        System.out.println(b);

        Porcino pepaPig = new Porcino(500f, LocalDate.now(), Sexo.MACHO, RazaPorcino.HAMPSHIRE);
        System.out.println(pepaPig);
    }

}
