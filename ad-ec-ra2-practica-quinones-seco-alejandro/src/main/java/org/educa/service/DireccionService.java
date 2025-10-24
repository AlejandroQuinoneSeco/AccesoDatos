package org.educa.service;

import org.educa.dao.DireccionDAOImpl;
import org.educa.entity.ClienteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class DireccionService {
    private final DireccionDAOImpl direccionDAO = new DireccionDAOImpl();

    public void saveDirecciones(Connection connection, ClienteEntity cliente) throws SQLException {
        direccionDAO.saveAll(connection, cliente.getDirecciones());
    }
}
