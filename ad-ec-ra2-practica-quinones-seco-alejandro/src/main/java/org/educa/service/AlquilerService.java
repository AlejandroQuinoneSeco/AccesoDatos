package org.educa.service;

import org.educa.entity.AlquilerEntity;
import org.educa.entity.ClienteEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class AlquilerService {
    private static final BigDecimal DESCUENTO = BigDecimal.valueOf(0.05);


    public List<AlquilerEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        //TODO
        return null;
    }

    public void save(AlquilerEntity alquiler) throws SQLException {
        //TODO
    }

    public void calculatePrecio(AlquilerEntity alquiler, int numAlquileres) {
        //TODO
    }
}
