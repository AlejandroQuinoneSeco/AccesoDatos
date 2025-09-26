package org.educa.entity;

import generated.Vehiculo;

import java.math.BigDecimal;

public class VehiculoEntity {
    private Vehiculo vehiculo;
    private BigDecimal precioFinal;
    private BigDecimal cost;
    private BigDecimal profit;

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String toPrint() {
        return "Matricula: " + vehiculo.getMatricula() + System.lineSeparator() +
                "Bastidor: " + vehiculo.getBastidor() + System.lineSeparator() +
                "Marca: " + vehiculo.getMarca() + System.lineSeparator() +
                "Modelo: " + vehiculo.getModelo() + System.lineSeparator() +
                "Color: " + vehiculo.getColor() + System.lineSeparator() +
                "Anio: " + vehiculo.getAnio() + System.lineSeparator() +
                "Categoria: " + vehiculo.getCategoria() + System.lineSeparator() +
                "Sucursal: " + vehiculo.getSucursal().getCalle() + " " + vehiculo.getSucursal().getCiudad() + " "
                + vehiculo.getSucursal().getPais() + " " + vehiculo.getSucursal().getCp() + System.lineSeparator() +
                "Combustible: " + vehiculo.getCombustible() + System.lineSeparator() +
                "Precio: " + vehiculo.getPrecio() + System.lineSeparator() +
                "Descuento: " + vehiculo.getDescuento() + System.lineSeparator() +
                "Precio final: " + getPrecioFinal() + System.lineSeparator() +
                "Coste: " + getCost() + System.lineSeparator() +
                "Beneficio: " + getProfit() + System.lineSeparator() +
                "====================================================";
    }
}
