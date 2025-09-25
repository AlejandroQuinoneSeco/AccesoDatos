package model;

public class FileInfo {
    private String nombre;
    private boolean directorio;
    private boolean puedeLeer;
    private boolean puedeEscribir;
    private boolean puedeEjecutar;
    private long tamano;

    public FileInfo(String nombre, boolean directorio, boolean puedeLeer,
                    boolean puedeEscribir, boolean puedeEjecutar, long tamano) {
        this.nombre = nombre;
        this.directorio = directorio;
        this.puedeLeer = puedeLeer;
        this.puedeEscribir = puedeEscribir;
        this.puedeEjecutar = puedeEjecutar;
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        String tipo = directorio ? "D" : "F";
        String permisos = "[" +
                (puedeEjecutar ? "X" : "") +
                (puedeLeer ? (permisosVacio(puedeEjecutar) ? "R" : ", R") : "") +
                (puedeEscribir ? (permisosVacio(puedeEjecutar || puedeLeer) ? "W" : ", W") : "")
                + "]";
        return nombre + "; " + tipo + "; " + permisos + "; " + tamano;
    }

    private boolean permisosVacio(boolean condicion) {
        return !condicion;
    }
}