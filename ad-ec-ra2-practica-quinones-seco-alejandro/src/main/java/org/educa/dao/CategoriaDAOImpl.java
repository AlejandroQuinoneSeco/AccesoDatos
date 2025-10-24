package org.educa.dao;

import org.educa.entity.CategoriaEntity;
import org.educa.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public CategoriaEntity findById(Integer idCategoria) throws SQLException {
        String sql = "SELECT id_categoria, nombre, descripcion, FROM categoria = ?";

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

    @Override
    public BigDecimal getPrecioPorDia(Integer idCategoria) throws SQLException {
        String sql = "SELECT precio_dia FROM categoria WHERE id_categoria = ?";

        try(Connection conn = ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("precio_dia");
                }
            }

        }
        return null;
    }
}