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

    @Override
    public SeguroEntity findById(Integer idSeguro) throws SQLException {
        String sql = "SELECT id_seguro, nombre, descripcion FROM seguro = ?";

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

    @Override
    public BigDecimal getPrecioPorDia(Integer idSeguro) throws SQLException {
        String sql = "SELECT precio_dia FROM seguro WHERE id_seguro = ?";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))  {

            ps.setInt(1, idSeguro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("precio_dia");
                }
            }
        }
        return null;
    }
}
