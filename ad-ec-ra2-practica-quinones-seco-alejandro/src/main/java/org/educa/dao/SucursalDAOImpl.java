package org.educa.dao;

import org.educa.entity.SucursalEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAOImpl implements SucursalDAO {

    /**
     * Obtiene todas las sucursales registradas en el sistema.
     * Las sucursales se devuelven ordenadas por ciudad y calle.
     *
     * @return una lista con todas las entidades SucursalEntity disponibles,
     *         o una lista vacía si no hay sucursales registradas
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public List<SucursalEntity> findAll() throws SQLException {
        List<SucursalEntity> sucursales = new ArrayList<>();
        String sql = "SELECT id_sucursal, calle, ciudad, pais, c_p FROM sucursal ORDER BY ciudad, calle";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SucursalEntity sucursal = new SucursalEntity();
                sucursal.setIdSucursal(rs.getInt("id_sucursal"));
                sucursal.setCalle(rs.getString("calle"));
                sucursal.setCiudad(rs.getString("ciudad"));
                sucursal.setPais(rs.getString("pais"));
                sucursal.setCodigoPostal(rs.getString("c_p"));
                sucursales.add(sucursal);
            }
        }
        return sucursales;
    }
}
