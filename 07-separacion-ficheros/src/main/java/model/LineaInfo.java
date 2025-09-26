package model;

public class LineaInfo {
    private String nombre;
    private boolean directorio;
    private String permisos;
    private long tamano;

    public LineaInfo(String nombre, boolean directorio, String permisos, long tamano) {
        this.nombre = nombre;
        this.directorio = directorio;
        this.permisos = permisos;
        this.tamano = tamano;
    }

    public boolean isDirectorio() {
        return directorio;
    }

    @Override
    public String toString() {
        return nombre + "; " + (directorio ? "D" : "F") + "; " + permisos + "; " + tamano;
    }
}
