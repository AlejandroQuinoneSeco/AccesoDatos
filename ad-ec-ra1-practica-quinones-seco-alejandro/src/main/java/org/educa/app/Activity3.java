package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.service.VehiculoService;

import java.io.IOException;
import java.text.ParseException;

public class Activity3 {
    private static final String PATH = "src/main/resources/export/";
    private static final String FILE_XML = "src/main/resources/xml/flota_junio2025.xml";

    public static void main(String[] args) {
        VehiculoService vehiculoService = new VehiculoService();
        try {
            vehiculoService.exportExcel(PATH, FILE_XML);
        } catch (JAXBException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
