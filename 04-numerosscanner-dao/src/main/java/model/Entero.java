package model;

/**
 * Clase que representa un número entero almacenado en el fichero.
 */
public class Entero {
    private int valor;

    public Entero(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
