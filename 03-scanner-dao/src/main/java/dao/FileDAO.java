package dao;

import model.FileData;

/**
 * Interfaz DAO que define las operaciones de acceso a datos
 * relacionadas con ficheros: guardar y leer.
 */
public interface FileDAO {
    void save(FileData fileData);   // Guarda contenido en un fichero
    String read(String fileName);   // Lee y devuelve contenido del fichero
}
