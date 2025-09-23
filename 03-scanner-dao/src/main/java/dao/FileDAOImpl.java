package dao;

import model.FileData;
import java.io.*;

/**
 * Implementación del DAO para trabajar con ficheros.
 * Usa FileWriter/BufferedWriter para escribir y
 * FileReader/BufferedReader para leer.
 */
public class FileDAOImpl implements FileDAO {

    /**
     * Guarda el contenido en el fichero especificado.
     * Si el fichero existe, se añade al final (append = true).
     */
    @Override
    public void save(FileData fileData) {
        try (FileWriter fw = new FileWriter(fileData.getFileName(), true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(fileData.getContent()); // Escribe el texto
            bw.newLine();                    // Salto de línea al final
        } catch (IOException e) {
            System.out.println("❌ Error al guardar en el fichero: " + e.getMessage());
        }
    }

    /**
     * Lee el contenido del fichero y lo devuelve como String.
     */
    @Override
    public String read(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Leer línea a línea y añadir al StringBuilder
            while((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer el fichero: " + e.getMessage());
        }
        return content.toString();
    }
}
