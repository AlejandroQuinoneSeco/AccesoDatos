package app;

import dao.EnteroDAO;
import dao.EnteroDAOImpl;
import model.Entero;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que gestiona la aplicación con DAO.
 */
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EnteroDAO dao = new EnteroDAOImpl();

        // 1. Mostrar fichero antes de añadir
        System.out.println("📂 Contenido del fichero antes de añadir:");
        mostrarFichero(dao);

        // 2. Pedir número al usuario
        System.out.print("Introduce un número entero: ");
        int valor = sc.nextInt();

        // 3. Guardar en el fichero
        dao.add(new Entero(valor));

        // 4. Mostrar fichero después de añadir
        System.out.println("\n📂 Contenido del fichero después de añadir:");
        mostrarFichero(dao);
    }

    private static void mostrarFichero(EnteroDAO dao) {
        List<Entero> numeros = dao.getAll();
        if (numeros.isEmpty()) {
            System.out.println("(Fichero vacío o inexistente)");
        } else {
            numeros.forEach(n -> System.out.print(n + " "));
            System.out.println("\n(Fin de fichero)");
        }
    }
}
