package org.educa.dao;

import org.educa.entity.DireccionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DireccionDAOImpl implements DireccionDAO {

    /**
     * Guarda múltiples direcciones en la base de datos de forma eficiente
     * utilizando batch dentro de una transacción.
     *
     * @param conn la conexión a la base de datos para usar en la transacción
     * @param direcciones la lista de direcciones a guardar, cada una debe tener
     *                    el id_cliente asignado
     * @throws SQLException si ocurre un error al insertar en la base de datos
     */
    @Override
    public void saveAll(Connection conn, List<DireccionEntity> direcciones) throws SQLException {
        String sql = "INSERT INTO direccion (calle, ciudad, pais, c_p, id_cliente) VALUES (?, ?, ?, ?, ?)";

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
