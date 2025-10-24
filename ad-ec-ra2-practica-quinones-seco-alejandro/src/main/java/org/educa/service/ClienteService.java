package org.educa.service;

import org.educa.dao.ClienteDAOImpl;
import org.educa.entity.ClienteEntity;
import org.educa.entity.DireccionEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteService {

    private ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
    private DireccionService direccionService = new DireccionService();

    public ClienteEntity findByDNI(String dni) throws SQLException {
        return clienteDAO.findByDni(dni);
    }

    public void saveCliente(ClienteEntity cliente) throws SQLException {
        try (Connection conn = ConnectionPool.getDataSource().getConnection()) {
            conn.setAutoCommit(false);

            try {
                clienteDAO.save(conn, cliente);
                if (!cliente.getDirecciones().isEmpty()){
                    for (DireccionEntity direccion : cliente.getDirecciones()) {
                        direccion.setIdCliente(cliente.getIdCliente());
                    }
                    direccionService.saveDirecciones(conn, cliente);
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
