package dao;


import model.FileInfo;
import java.io.File;
import java.util.List;

public interface FileDAO {
    List<FileInfo> listarFicheros(File carpeta);
}
