package org.educa.dao;

import org.educa.entity.DireccionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DireccionDAOImpl implements DireccionDAO {

    @Override
    public void saveAll(Connection conn, List<DireccionEntity> direcciones) throws SQLException {
        String sql = "INSERT into direccion (calle, ciudad, pais, cp, id_cliente) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (DireccionEntity direccion : direcciones) {
                ps.setString(1, direccion.getCalle());
                ps.setString(2, direccion.getCiudad());
                ps.setString(3, direccion.getPais());
                ps.setString(4, direccion.getCp());
                ps.setInt(5, direccion.getIdCliente());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
