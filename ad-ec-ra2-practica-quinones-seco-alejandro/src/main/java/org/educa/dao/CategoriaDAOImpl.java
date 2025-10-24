package org.educa.dao;

import org.educa.entity.CategoriaEntity;
import org.educa.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAOImpl implements CategoriaDAO {

    /**
     * Busca una categoría por su identificador único.
     *
     * @param idCategoria el identificador único de la categoría
     * @return la entidad CategoriaEntity encontrada, o null si no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public CategoriaEntity findById(Integer idCategoria) throws SQLException {
        String sql = "SELECT id_categoria, nombre, descripcion FROM categoria WHERE id_categoria = ?";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoriaEntity categoria = new CategoriaEntity();
                    categoria.setIdCategoria(rs.getInt("id_categoria"));
                    categoria.setNombre(rs.getString("nombre"));
                    categoria.setDescripcion(rs.getString("descripcion"));
                    return categoria;
                }
            }
        }
        return null;
    }
    /**
     * Obtiene el precio por día asociado a una categoría específica.
     *
     * @param idCategoria el identificador único de la categoría
     * @return el precio por día de la categoría como BigDecimal,
     *         o null si la categoría no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public BigDecimal getPrecioPorDia(Integer idCategoria) throws SQLException {
        String sql = "SELECT nombre FROM categoria WHERE id_categoria = ?";

        try(Connection conn = ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    // Mapeo según especificaciones de la práctica
                    return switch (nombre) {
                        case "M" -> new BigDecimal("10.00");
                        case "E" -> new BigDecimal("20.00");
                        case "C" -> new BigDecimal("70.00");
                        case "S" -> new BigDecimal("120.00");
                        case "P" -> new BigDecimal("200.00");
                        default -> BigDecimal.ZERO;
                    };
                }
            }
        }
        return BigDecimal.ZERO;
    }
}