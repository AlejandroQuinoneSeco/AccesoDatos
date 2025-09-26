package dao;

import model.LineaInfo;
import java.util.List;

public interface ListadoDAO {
    List<LineaInfo> leerFichero(String rutaFichero);
}
