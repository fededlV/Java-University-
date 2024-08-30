public class Prueba {
    public static void main(String[] args) {
        Fraccion f1 = new Fraccion(3, 4);
        System.out.println(f1);
        System.out.println(f1.Resultado(f1));
        Fraccion f2 = new Fraccion(4, 5);
        Fraccion resultado = f1.sumar(f2);
        System.out.println("Resultado:" + resultado);
    }
}
