package puertocarga;

public class Barco {
    private String matricula;
    private int nroMuelle; 
    private double capacidad;
    private double costoPorHora;
    private Capitan capitan;

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public int getNroMuelle() {
        return nroMuelle;
    }
    public void setNroMuelle(int nroMuelle) {
        this.nroMuelle = nroMuelle;
    }
    public double getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }
    public double getCostoPorHora() {
        return costoPorHora;
    }
    public void setCostoPorHora(double costoPorHora) {
        this.costoPorHora = costoPorHora;
    }
    public Capitan getCapitan() {
        return capitan;
    }
    public void setCapitan(Capitan capitan) {
        this.capitan = capitan;
    }

    public Barco(String matricula, int nroMuelle, double capacidad, double costoPorHora, Capitan capitan) {
        this.matricula = matricula;
        this.nroMuelle = nroMuelle;
        this.capacidad = capacidad;
        this.costoPorHora = costoPorHora;
        this.capitan = capitan;
    }

    @Override
    public String toString(){
        return "Barco[Matricula: " + matricula + ", Numero de Muelle: " + nroMuelle + ", Capacidad de carga: " + capacidad + "toneladas, Costo Por Hora: " + costoPorHora + ", " + capitan + "]";
    }

    

}
