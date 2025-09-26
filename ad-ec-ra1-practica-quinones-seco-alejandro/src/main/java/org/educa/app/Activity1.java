package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.VehiculoEntity;
import org.educa.service.VehiculoService;

import java.util.List;

public class Activity1 {
    private static final String FILE_XML = "src/main/resources/xml/flota_junio2025.xml";

    public static void main(String[] args) {
        //Leer el fichero XML
        VehiculoService vehiculoService = new VehiculoService();
        try {
            List<VehiculoEntity> vehiculos = vehiculoService.readFile(FILE_XML);
            for (VehiculoEntity vehiculo : vehiculos) {
                //Pintar por consola
                System.out.println(vehiculo.toPrint());
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
