package org.educa.service;

import org.educa.dao.SucursalDAOImpl;
import org.educa.entity.SucursalEntity;

import java.sql.SQLException;
import java.util.List;

public class SucursalService {
    private final SucursalDAOImpl sucursalDAO = new SucursalDAOImpl();
    public List<SucursalEntity> findAll() throws SQLException {
        return sucursalDAO.findAll();
    }
}
