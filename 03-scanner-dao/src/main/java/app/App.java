package app;

import dao.FileDAO;
import dao.FileDAOImpl;
import model.FileData;

import java.util.Scanner;

/**
 * Clase principal que maneja la lógica de la aplicación.
 * Usa la capa DAO para interactuar con los ficheros.
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Pedir texto al usuario
        System.out.print("Introduce un texto: ");
        String texto = sc.nextLine();

        // 2. Pedir nombre de fichero
        System.out.print("Introduce el nombre del fichero (ej: salida.txt): ");
        String fileName = sc.nextLine();

        // 3. Crear DAO para manejar el fichero
        FileDAO fileDAO = new FileDAOImpl();

        // 4. Guardar el contenido en el fichero
        FileData fileData = new FileData(fileName, texto);
        fileDAO.save(fileData);

        // 5. Leer y mostrar el contenido completo del fichero
        System.out.println("\n📂 Contenido actual del fichero:");
        String contenido = fileDAO.read(fileName);
        System.out.println(contenido);
    }
}
