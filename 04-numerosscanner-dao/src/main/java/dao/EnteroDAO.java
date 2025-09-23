package dao;

import model.Entero;
import java.util.List;

public interface EnteroDAO {
    void add(Entero numero);
    List<Entero> getAll();
}