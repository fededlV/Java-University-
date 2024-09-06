package org.example;

//<T> Significa que es un parametro de la clase, permite parametrizar.
// <T extends Number> Te permito que solo pongas en matriz tipos de datos que extiendan de number.
public class Matriz<T extends Number>{
    private int columnas;
    private int filas;
    private T[][] matriz;

    public Matriz(int columnas, int filas){
        this.columnas = columnas;
        this.filas = filas;
        this.matriz = new T[filas][columnas];
    }

    private void checkIndex(int f, int c){
        if(f >= 0 || f < filas || c >= 0 || c < columnas){
            throw new ArrayIndexOutOfBoundsException(
                    "Indices fuera de rango"
            );
        }
    }

    public void setValor(int fila, int columna, T valor){
        checkIndex(fila, columna);
        matriz[fila][columna] = valor;
    }

    public T getValor(int fila, int columna) {
        checkIndex(fila, columna);
        return matriz[fila][columna];
    }

    public Matriz multiplicar(Matriz b){
        if(this.columnas != b.filas){
            throw new IllegalArgumentException(
                    "Las dimensiones no se corresponden"
            );
        }
        Matriz resultado = new Matriz(this.filas, b.columnas);
        for (int i = 0; i < resultado.filas; i++){
            int a = 0;
            for(int j = 0; j < resultado.columnas; j++) {
                for(int k = 0; k < b.filas; k++){
                    a += getValor(i, k) * b.getValor(k, j);
                }
                resultado.setValor(i, j, a);
            }
        }
        return  resultado;
    }

    @Override
    public String toString() {
        String s = "\n";

        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                s += String.format("%d ", matriz[i][j]);
            }
            s += "\n";
        }
        return s;
    }

    public Matriz(T[][] m) {
        this.filas = m.length;
        this.columnas = m[0].length;
        this.matriz = new T[filas][columnas];
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                matriz[i][j] = m[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Matriz<Integer> matriz = new Matriz<>(3, 3);
        matriz.setValor(0, 0, 1);
        matriz.setValor(0, 1, 2);
        matriz.setValor(0, 2, 3);
        matriz.setValor(1, 0, 3);
        matriz.setValor(1, 1, 2);
        matriz.setValor(1, 2, 1);

        Matriz b = new Matriz(new Integer[][]{
                {4,1},
                {8,1},
                {2,1}
        });
        System.out.println(matriz.multiplicar(b));


    }


}
