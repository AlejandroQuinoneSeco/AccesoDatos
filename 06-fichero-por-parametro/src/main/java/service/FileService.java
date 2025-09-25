package service;

import dao.FileDAO;
import dao.FileDAOImpl;
import model.FileInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileService {
    private FileDAO fileDAO;

    public FileService() {
        this.fileDAO = new FileDAOImpl();
    }

    public void generarListado(String rutaCarpeta, String rutaSalida) {
        File carpeta = new File(rutaCarpeta);
        List<FileInfo> ficheros = fileDAO.listarFicheros(carpeta);

        try (FileWriter writer = new FileWriter(rutaSalida)) {
            for (FileInfo fi : ficheros) {
                writer.write(fi.toString() + "\n");
            }
            System.out.println("Listado generado en: " + rutaSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
