package dao;

import model.Entero;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de EnteroDAO usando un fichero binario.
 */
public class EnteroDAOImpl implements EnteroDAO {

    private final String FILE_NAME = "enteros.dat";

    @Override
    public void add(Entero numero) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME, true))) {
            dos.writeInt(numero.getValor());
        } catch (IOException e) {
            System.out.println("❌ Error al añadir número: " + e.getMessage());
        }
    }

    @Override
    public List<Entero> getAll() {
        List<Entero> numeros = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                int n = dis.readInt();
                numeros.add(new Entero(n));
            }
        } catch (EOFException e) {
            // Fin del fichero → se ignora
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ El fichero no existe todavía.");
        } catch (IOException e) {
            System.out.println("❌ Error de lectura: " + e.getMessage());
        }
        return numeros;
    }
}
