package org.educa.service;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.VehiculoEntity;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class VehiculoService {

    public List<VehiculoEntity> readFile(String fileXml) throws JAXBException {
        //TODO: Implementar
        return null;
    }

    public List<VehiculoEntity> readFile(File file) throws JAXBException {
        //TODO: Implementar
        return null;
    }

    public void exportSummary(String path, String fileXml) throws JAXBException, IOException {
        //TODO: Implementar

    }

    public void exportExcel(String path, String fileXml) throws JAXBException, IOException, ParseException {
        //TODO: Implementar
    }
}
