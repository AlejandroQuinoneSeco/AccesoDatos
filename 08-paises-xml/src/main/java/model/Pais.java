package model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Pais {

    @XmlAttribute(name = "nombre", required = true)
    private String nombre;

    @XmlElement(name = "capital", required = true)
    private String capital;

    @XmlElement(name = "poblacion", required = true)
    private double poblacion;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    public double getPoblacion() { return poblacion; }
    public void setPoblacion(double poblacion) { this.poblacion = poblacion; }

    @Override
    public String toString() {
        return "Pais{" +
                "nombre='" + nombre + '\'' +
                ", capital='" + capital + '\'' +
                ", poblacion=" + poblacion +
                '}';
    }
}
