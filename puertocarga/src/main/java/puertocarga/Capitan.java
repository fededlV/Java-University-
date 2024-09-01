package puertocarga;



public class Capitan {
    private String id;
    private String nombre;
    private String apellido;
    private int antiguedad;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getAntiguedad() {
        return antiguedad;
    }
    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    } 

    public Capitan(String id, String nombre, String apellido, int antiguedad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
    }

    public String toString(){
        return "Capitan[Identificador: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Antiguedad: " + antiguedad + " años]";
    }

    

}
