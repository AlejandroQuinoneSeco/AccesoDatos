package org.educa.dao;

import org.educa.entity.AlquilerEntity;
import org.educa.entity.ClienteEntity;
import org.educa.entity.SeguroEntity;
import org.educa.entity.VehiculoEntity;
import org.educa.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAOImpl implements AlquilerDAO {
    /**
     * Busca todos los alquileres realizados por un cliente específico.
     * Los alquileres se devuelven ordenados por fecha de inicio de forma descendente.
     *
     * @param idCliente el identificador único del cliente
     * @return una lista de entidades AlquilerEntity correspondientes al cliente,
     *         o una lista vacía si el cliente no tiene alquileres
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public List<AlquilerEntity> findByCliente(Integer idCliente) throws SQLException {
        List<AlquilerEntity> alquileres = new ArrayList<>();
        String sql = "SELECT a.id_alquiler, a.fecha_ini, a.fecha_fin, a.precio, a.id_cliente, " +
                "a.id_vehiculo, a.id_seguro " +  // CORREGIDO: quité el espacio antes de id_seguro
                "FROM alquiler a " +
                "WHERE a.id_cliente = ? " +
                "ORDER BY a.fecha_ini DESC";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AlquilerEntity alquiler = new AlquilerEntity();
                    alquiler.setIdAlquiler(rs.getInt("id_alquiler"));
                    alquiler.setFechaIni(rs.getDate("fecha_ini").toLocalDate());
                    alquiler.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                    alquiler.setPrecio(rs.getBigDecimal("precio"));

                    // AÑADIDO: Crear y asignar el vehículo con su ID
                    VehiculoEntity vehiculo = new VehiculoEntity();
                    vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                    alquiler.setVehiculo(vehiculo);

                    // AÑADIDO: Crear y asignar el seguro con su ID
                    SeguroEntity seguro = new SeguroEntity();
                    seguro.setIdSeguro(rs.getInt("id_seguro"));
                    alquiler.setSeguro(seguro);

                    // AÑADIDO: Crear y asignar el cliente con su ID
                    ClienteEntity cliente = new ClienteEntity();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    alquiler.setCliente(cliente);

                    alquileres.add(alquiler);
                }
            }
        }
        return alquileres;
    }

    /**
     * Guarda un nuevo alquiler en la base de datos.
     * Tras la inserción, actualiza el ID del alquiler en la entidad proporcionada.
     *
     * @param alquiler la entidad AlquilerEntity a guardar, debe contener todos
     *                 los datos necesarios (cliente, vehículo, seguro, fechas y precio)
     * @throws SQLException si ocurre un error al insertar en la base de datos
     */
    @Override
    public void save(AlquilerEntity alquiler) throws SQLException {
        String sql = "INSERT INTO alquiler (fecha_ini, fecha_fin, id_cliente, id_vehiculo, id_seguro, precio) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id_alquiler";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))  {

            ps.setDate(1, Date.valueOf(alquiler.getFechaIni()));
            ps.setDate(2, Date.valueOf(alquiler.getFechaFin()));
            ps.setInt(3, alquiler.getCliente().getIdCliente());
            ps.setInt(4, alquiler.getVehiculo().getIdVehiculo());
            ps.setInt(5, alquiler.getSeguro().getIdSeguro());
            ps.setBigDecimal(6, alquiler.getPrecio());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    alquiler.setIdAlquiler(rs.getInt("id_alquiler"));
                }
            }
        }
    }
}