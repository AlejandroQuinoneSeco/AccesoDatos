package org.educa.dao;

import org.educa.entity.DireccionEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DireccionDAO {

    void saveAll (Connection conn, List<DireccionEntity> direcciones) throws SQLException;
}
