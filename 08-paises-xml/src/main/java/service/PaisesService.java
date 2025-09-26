package service;

import dao.PaisesDAO;
import dao.PaisesDAOImpl;
import model.Pais;
import model.Paises;

public class PaisesService {
    private final PaisesDAO dao = new PaisesDAOImpl();

    public void mostrarPaises(String rutaXML) {
        Paises paises = dao.cargarDesdeXML(rutaXML);
        if (paises != null && paises.getPaises() != null) {
            for (Pais p : paises.getPaises()) {
                System.out.println(p);
            }
        } else {
            System.out.println("No se pudieron cargar los países.");
        }
    }
}
