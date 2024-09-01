package puertocarga;

public class Main {
    public static void main(String[] args) {
        
        //2. Cargar las embarcaciones del archivo csv en un array de objetos del tipo Barco
        Barco[] barcos = CargarDatos.cargarBarcos("puertocarga/barcos.csv", 500);

        Puerto puerto = new Puerto();
        for (int i = 0; i < barcos.length; i++){
            puerto.setBarcos(barcos[i]);
        }

        //3. Asumiendo que el tiempo promedio de carga de una embarcacion son 15 hs, cual seria el total de carga que recaudaria el puerto con todos los barcos amarrados (barcos cargados en el array)
        System.out.println("Costo total de todos los barcos.");
        int total = 0;
        for (int i = 0; i < barcos.length; i++){
            total += puerto.cargaTotal(15, barcos[i]);
        }
        System.out.println("Total: " + total);

        //4. Informar todos los barcos, en un listado, cuyo capitan tiene mas de 18 años de experiencia.
        //Falta que lo informe en forma de listado.
        System.out.println("Barcos con capitanes con mas de 18 años de experiencia.");
        puerto.barcosConCapitanMasDe18Anios();

        //5.Determinar carga promedio de todos los barcos en posiciones pares de amarre. 
        System.out.println("Carga promedio de los barcos en posiciones pares.");
        System.out.println(puerto.cargaPromedioBarcosPosicionesPares());
    }
}

