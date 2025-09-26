package dao;

import model.LineaInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ListadoDAOImpl implements ListadoDAO {

    @Override
    public List<LineaInfo> leerFichero(String rutaFichero) {
        List<LineaInfo> lista = new ArrayList<>();
        File fichero = new File(rutaFichero);

        if (!fichero.exists()) {
            System.out.println("Fichero de entrada no encontrado: " + rutaFichero);
            return lista;
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fichero), Charset.forName("ISO-8859-1")))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Ejemplo línea: "ACCESO A DATOS; D; [X, R, W]; 4096"
                String[] partes = linea.split("; ");
                if (partes.length != 4) continue;

                String nombre = partes[0];
                boolean directorio = partes[1].equalsIgnoreCase("D");
                String permisos = partes[2];
                long tamano = Long.parseLong(partes[3]);

                lista.add(new LineaInfo(nombre, directorio, permisos, tamano));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
