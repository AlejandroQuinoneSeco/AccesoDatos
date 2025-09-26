package service;

import dao.ListadoDAO;
import dao.ListadoDAOImpl;
import model.LineaInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class SplitService {
    private ListadoDAO listadoDAO;

    public SplitService() {
        this.listadoDAO = new ListadoDAOImpl();
    }

    public void separar(String rutaEntrada, String salidaDirs, String salidaFiles) {
        List<LineaInfo> lineas = listadoDAO.leerFichero(rutaEntrada);
        Charset charset = Charset.forName("ISO-8859-1");

        try (
                BufferedWriter bwDirs = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salidaDirs), charset));
                BufferedWriter bwFiles = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salidaFiles), charset))
        ) {
            for (LineaInfo li : lineas) {
                if (li.isDirectorio()) {
                    bwDirs.write(li.toString());
                    bwDirs.newLine();
                } else {
                    bwFiles.write(li.toString());
                    bwFiles.newLine();
                }
            }
            System.out.println("✅ Ficheros separados correctamente:");
            System.out.println(" - Directorios: " + salidaDirs);
            System.out.println(" - Ficheros: " + salidaFiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
