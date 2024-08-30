public class Fraccion {
    private int numerador;
    private int denominador;

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public Fraccion(int numerador, int denominador){
        this.numerador = numerador;
        this.denominador = denominador;
    }

    @Override
    public String toString() {
        return numerador + "/" + denominador;
    }

    public Fraccion(int numerador){
        this(numerador, 1);
    }

    public Fraccion(Fraccion f){
        this(f.numerador, f.denominador);
    }

    public double Resultado(Fraccion f){
        double resp = f.numerador / (double) f.denominador;
        return resp;
    }

    // Método para sumar fracciones
    public Fraccion sumar(Fraccion otra) {
        // Calcular el nuevo numerador
        int nuevoNumerador = this.numerador * otra.getDenominador() + otra.getNumerador() * this.denominador;
        // Calcular el nuevo denominador
        int nuevoDenominador = this.denominador * otra.getDenominador();
        // Retornar una nueva fracción con los valores calculados
        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }

    // Método para simplificar la fracción
    private int mcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return mcd(b, a % b);
    }


}
