package org.educa.dao;

import org.educa.entity.SucursalEntity;

import java.sql.SQLException;
import java.util.List;

public interface SucursalDAO {
    public List<SucursalEntity> findAll() throws SQLException;
}
