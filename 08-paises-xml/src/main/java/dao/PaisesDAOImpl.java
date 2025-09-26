package dao;

import model.Paises;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class PaisesDAOImpl implements PaisesDAO {

    @Override
    public Paises cargarDesdeXML(String rutaXML) {
        try {
            JAXBContext context = JAXBContext.newInstance(Paises.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Paises) unmarshaller.unmarshal(new File(rutaXML));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
