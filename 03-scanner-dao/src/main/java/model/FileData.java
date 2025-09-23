package model;

/**
 * Clase modelo que representa los datos de un fichero.
 * Contiene el nombre del fichero y el contenido a guardar.
 */

public class FileData {
    private String fileName;
    private String content;

    public FileData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }
}
