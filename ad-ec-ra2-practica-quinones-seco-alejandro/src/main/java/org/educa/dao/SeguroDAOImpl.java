package org.educa.dao;

import org.educa.entity.SeguroEntity;
import org.educa.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAOImpl implements SeguroDAO {

    /**
     * Obtiene todos los seguros disponibles en el sistema.
     * Los seguros se devuelven ordenados por su identificador.
     *
     * @return una lista con todas las entidades SeguroEntity disponibles,
     *         o una lista vacía si no hay seguros registrados
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public List<SeguroEntity> findAll() throws SQLException {
        List<SeguroEntity> seguros = new ArrayList<>();
        String sql = "SELECT id_seguro, nombre, descripcion FROM seguro ORDER BY id_seguro";

        try(Connection conn = ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                SeguroEntity seguro = new SeguroEntity();
                seguro.setIdSeguro(rs.getInt("id_seguro"));
                seguro.setNombre(rs.getString("nombre"));
                seguro.setDescripcion(rs.getString("descripcion"));
                seguros.add(seguro);
            }

        }
        return seguros;
    }

    /**
     * Busca un seguro por su identificador único.
     *
     * @param idSeguro el identificador único del seguro
     * @return la entidad SeguroEntity encontrada, o null si no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public SeguroEntity findById(Integer idSeguro) throws SQLException {
        String sql = "SELECT id_seguro, nombre, descripcion FROM seguro WHERE id_seguro = ?";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSeguro);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    SeguroEntity seguro = new SeguroEntity();
                    seguro.setIdSeguro(rs.getInt("id_seguro"));
                    seguro.setNombre(rs.getString("nombre"));
                    seguro.setDescripcion(rs.getString("descripcion"));
                    return seguro;
                }
            }
        }
        return null;
    }

    /**
     * Obtiene el precio por día de un seguro específico.
     *
     * @param idSeguro el identificador único del seguro
     * @return el precio por día del seguro como BigDecimal,
     *         o null si el seguro no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public BigDecimal getPrecioPorDia(Integer idSeguro) throws SQLException {
        String sql = "SELECT nombre FROM seguro WHERE id_seguro = ?";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))  {

            ps.setInt(1, idSeguro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    // Mapeo según especificaciones de la práctica
                    return switch (nombre) {
                        case "A terceros" -> new BigDecimal("5.00");
                        case "Franquicia 300" -> new BigDecimal("20.00");
                        case "Franquicia 150" -> new BigDecimal("35.00");
                        case "A todo riesgo" -> new BigDecimal("50.00");
                        default -> BigDecimal.ZERO;
                    };
                }
            }
        }
        return BigDecimal.ZERO;
    }
}
