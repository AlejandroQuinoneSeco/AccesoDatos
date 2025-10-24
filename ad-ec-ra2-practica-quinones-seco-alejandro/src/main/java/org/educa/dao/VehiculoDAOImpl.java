package org.educa.dao;

import org.educa.entity.CategoriaEntity;
import org.educa.entity.SucursalEntity;
import org.educa.entity.VehiculoEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAOImpl implements VehiculoDAO {

    @Override
    public List<VehiculoEntity> findBySucursal(Integer idSucursal) throws SQLException {
        List<VehiculoEntity> vehiculos = new ArrayList<>();
        String sql = "SELECT v.id_vehiculo, v.matricula, v.bastidor, v.marca, v.modelo, v.color, v.anio, " +
                "v.id_categoria, v.id_sucursal, v.id_comb, " +
                "c.nombre AS cat_nombre, c.descripcion AS cat_desc, " +
                "s.calle, s.ciudad, s.pais, s.c_p " +
                "FROM vehiculo v " +
                "INNER JOIN categoria c ON v.id_categoria = c.id_categoria " +
                "INNER JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
                "WHERE v.id_sucursal = ? " +
                "ORDER BY v.marca, v.modelo";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSucursal);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VehiculoEntity vehiculo = new VehiculoEntity();
                    vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                    vehiculo.setMatricula(rs.getString("matricula"));
                    vehiculo.setBastidor(rs.getString("bastidor"));
                    vehiculo.setMarca(rs.getString("marca"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setColor(rs.getString("color"));
                    vehiculo.setAnio(rs.getInt("anio"));
                    vehiculo.setIdComb(rs.getInt("id_comb"));

                    CategoriaEntity categoria = new CategoriaEntity();
                    categoria.setIdCategoria(rs.getInt("id_categoria"));
                    categoria.setNombre(rs.getString("cat_nombre"));
                    categoria.setDescripcion(rs.getString("cat_desc"));
                    vehiculo.setCategoria(categoria);

                    SucursalEntity sucursal = new SucursalEntity();
                    sucursal.setIdSucursal(rs.getInt("id_sucursal"));
                    sucursal.setCalle(rs.getString("calle"));
                    sucursal.setCiudad(rs.getString("ciudad"));
                    sucursal.setPais(rs.getString("pais"));
                    sucursal.setCodigoPostal(rs.getString("c_p"));
                    vehiculo.setSucursal(sucursal);

                    vehiculos.add(vehiculo);
                }
            }
        }
        return vehiculos;
    }

    @Override
    public VehiculoEntity findById(Integer idVehiculo) throws SQLException {
        String sql = "SELECT v.id_vehiculo, v.matricula, v.bastidor, v.marca, v.modelo, v.color, v.anio, " +
                "v.id_categoria, v.id_sucursal, v.id_comb, " +
                "c.nombre AS cat_nombre, c.descripcion AS cat_desc, " +
                "s.calle, s.ciudad, s.pais, s.c_p " +
                "FROM vehiculo v " +
                "INNER JOIN categoria c ON v.id_categoria = c.id_categoria " +
                "INNER JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
                "WHERE v.id_vehiculo = ?";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVehiculo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    VehiculoEntity vehiculo = new VehiculoEntity();
                    vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                    vehiculo.setMatricula(rs.getString("matricula"));
                    vehiculo.setBastidor(rs.getString("bastidor"));
                    vehiculo.setMarca(rs.getString("marca"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setColor(rs.getString("color"));
                    vehiculo.setAnio(rs.getInt("anio"));
                    vehiculo.setIdComb(rs.getInt("id_comb"));

                    CategoriaEntity categoria = new CategoriaEntity();
                    categoria.setIdCategoria(rs.getInt("id_categoria"));
                    categoria.setNombre(rs.getString("cat_nombre"));
                    categoria.setDescripcion(rs.getString("cat_desc"));
                    vehiculo.setCategoria(categoria);

                    SucursalEntity sucursal = new SucursalEntity();
                    sucursal.setIdSucursal(rs.getInt("id_sucursal"));
                    sucursal.setCalle(rs.getString("calle"));
                    sucursal.setCiudad(rs.getString("ciudad"));
                    sucursal.setPais(rs.getString("pais"));
                    sucursal.setCodigoPostal(rs.getString("c_p"));
                    vehiculo.setSucursal(sucursal);

                    return vehiculo;
                }
            }
        }
        return null;
    }
}