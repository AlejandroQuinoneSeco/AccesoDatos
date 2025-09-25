package dao;

import model.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl implements FileDAO {
    @Override
    public List<FileInfo> listarFicheros(File carpeta) {
        List<FileInfo> listaFicheros = new ArrayList<>();
        if (carpeta.isDirectory()) {
            for (File fichero : carpeta.listFiles()) {
                FileInfo info = new FileInfo(
                        fichero.getName(),
                        fichero.isDirectory(),
                        fichero.canRead(),
                        fichero.canWrite(),
                        fichero.canExecute(),
                        fichero.length()
                );
                listaFicheros.add(info);
            }
        }
        return listaFicheros;
    }
}
