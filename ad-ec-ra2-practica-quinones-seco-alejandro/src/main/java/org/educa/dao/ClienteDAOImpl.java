package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.pool.ConnectionPool;

import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO {

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni el DNI del cliente a buscar
     * @return la entidad ClienteEntity encontrada, o null si no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public ClienteEntity findByDni(String dni) throws SQLException {
        String sql = "SELECT id_cliente, nombre, p_apellido, s_apellido, email, dni, telefono " +
                "FROM cliente WHERE dni = ?";

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery())  {
                if (rs.next()) {
                    ClienteEntity cliente = new ClienteEntity();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setPrimerApellido(rs.getString("p_apellido"));
                    cliente.setSegundoApellido(rs.getString("s_apellido"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setDni(rs.getString("dni"));
                    cliente.setTelefono(rs.getString("telefono"));
                    return cliente;
                }
            }
        }
        return null;
    }

    /**
     * Guarda un nuevo cliente en la base de datos dentro de una transacción.
     * Tras la inserción, actualiza el ID del cliente en la entidad proporcionada.
     *
     * @param connection la conexión a la base de datos para usar en la transacción
     * @param cliente la entidad ClienteEntity a guardar con todos sus datos
     * @throws SQLException si ocurre un error al insertar en la base de datos
     */
    @Override
    public void save(Connection connection, ClienteEntity cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nombre, p_apellido, s_apellido, email, dni, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id_cliente";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getPrimerApellido());
            ps.setString(3, cliente.getSegundoApellido());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getDni());
            ps.setString(6, cliente.getTelefono());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                }
            }
        }
    }
}
