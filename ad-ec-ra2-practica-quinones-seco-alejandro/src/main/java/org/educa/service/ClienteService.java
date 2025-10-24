package org.educa.service;

import org.educa.dao.ClienteDAOImpl;
import org.educa.entity.ClienteEntity;
import org.educa.entity.DireccionEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Servicio de lógica de negocio para la gestión de clientes.
 * Proporciona funcionalidades para buscar y registrar clientes,
 * gestionando transacciones para garantizar la consistencia de los datos.
 */
public class ClienteService {

    private ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
    private DireccionService direccionService = new DireccionService();

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni el DNI del cliente a buscar
     * @return la entidad ClienteEntity encontrada, o null si no existe un cliente con ese DNI
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public ClienteEntity findByDNI(String dni) throws SQLException {
        return clienteDAO.findByDni(dni);
    }

    /**
     * Guarda un nuevo cliente en el sistema junto con todas sus direcciones.
     * Esta operación se realiza dentro de una transacción para garantizar
     * la consistencia de los datos. Si ocurre algún error, se realiza rollback
     * automático.
     *
     * @param cliente la entidad ClienteEntity a guardar, incluyendo su lista de direcciones
     * @throws SQLException si ocurre un error durante la transacción de guardado
     */
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
