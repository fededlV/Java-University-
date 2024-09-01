package puertocarga;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CargarDatos {
    public static Barco[] cargarBarcos(String nombreArchivo, int maxBarcos) {
        Barco[] barcos = new Barco[maxBarcos];
        int indice = 0;
        try (Scanner sc = new Scanner(new File(nombreArchivo))) {
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                
                Capitan capitan = new Capitan(parts[4], 
                parts[5], 
                parts[6], 
                Integer.parseInt(parts[7]));

                Barco barco = new Barco(parts[0], 
                Integer.parseInt(parts[1]), 
                Double.parseDouble(parts[2]), 
                Double.parseDouble(parts[3]), 
                capitan);
                
                if(indice < barcos.length){
                    barcos[indice] = barco;
                    indice++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return barcos;
    }
}
