package org.educa.dao;

import org.educa.entity.AlquilerEntity;

import java.sql.SQLException;
import java.util.List;


public interface AlquilerDAO {

    public List<AlquilerEntity> findByCliente (Integer idCliente) throws SQLException;

    public void save(AlquilerEntity alquiler) throws SQLException;
}
