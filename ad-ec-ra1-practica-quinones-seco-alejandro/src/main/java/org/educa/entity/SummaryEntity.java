package org.educa.entity;

import java.math.BigDecimal;

public class SummaryEntity {
    private String name;
    private int numberOfVehicles;
    private BigDecimal totalProfit;
    private String filePath;
    private String fileName;
    private long fileSize;

    public SummaryEntity() {
    }

    public SummaryEntity(String name, int numberOfVehicles, BigDecimal totalProfit, String filePath,
                         String fileName, long fileSize) {
        this.name = name;
        this.numberOfVehicles = numberOfVehicles;
        this.totalProfit = totalProfit;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String toPrint() {
        return "Fecha: " + getName() + System.lineSeparator() +
                "NumeroDeVehiculos: " + getNumberOfVehicles() + System.lineSeparator() +
                "BeneficioTotal: " + getTotalProfit() + System.lineSeparator() +
                "Ruta del Fichero: " + getFilePath() + System.lineSeparator() +
                "Nombre del Fichero: " + getFileName() + System.lineSeparator() +
                "Tamaño del Fichero: " + getFileSize() + " bytes";
    }
}
