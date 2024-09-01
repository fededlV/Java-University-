package puertocarga;

import java.util.ArrayList;
import java.util.List;

public class Puerto {
    private List<Barco> barcos;

    public Puerto() {
        this.barcos = new ArrayList<>();
    }

    public List<Barco> getBarcos() {
        return barcos;
    }
    
    public void setBarcos(Barco barco) {
        barcos.add(barco);
    }
    
    public void mostrarBarcos() {
        for (Barco barco : barcos) {
            System.out.println(barco);
        }
    }

    public int cargaTotal(int tiempoCarga, Barco barco) {
        int total = 0;
        for (Barco b : barcos) {
            total += (int) b.getCostoPorHora();
        }
        /* if (tiempoCarga > 0) {
            total += (int) ((int) tiempoCarga * barco.getCostoPorHora());
        } */
        return total;
    }

    public void barcosConCapitanMasDe18Anios() {
        for (Barco barco : barcos) {
            if (barco.getCapitan().getAntiguedad() > 18) {
                System.out.println(barco.getMatricula());
            }
        }
    }

    public int cargaPromedioBarcosPosicionesPares(){
        int suma = 0;
        int total = 0;
        for(Barco b : barcos){
            if(b.getNroMuelle() % 2 == 0){
                suma += b.getCapacidad();
                total++;
            }
        }
        return suma / total; 
    }
    
    
}
