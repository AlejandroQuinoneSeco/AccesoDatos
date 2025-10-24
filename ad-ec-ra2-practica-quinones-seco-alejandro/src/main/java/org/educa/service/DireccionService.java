package org.educa.service;

import org.educa.dao.DireccionDAOImpl;
import org.educa.entity.ClienteEntity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Servicio de lógica de negocio para la gestión de direcciones de clientes.
 * Proporciona funcionalidades para guardar direcciones dentro de transacciones.
 */
public class DireccionService {
    private final DireccionDAOImpl direccionDAO = new DireccionDAOImpl();

    /**
     * Guarda todas las direcciones de un cliente en la base de datos.
     * Este metodo se utiliza dentro de transacciones gestionadas por ClienteService.
     *
     * @param connection la conexión de base de datos activa para la transacción
     * @param cliente la entidad ClienteEntity con la lista de direcciones a guardar
     * @throws SQLException si ocurre un error al guardar las direcciones
     */
    public void saveDirecciones(Connection connection, ClienteEntity cliente) throws SQLException {
        direccionDAO.saveAll(connection, cliente.getDirecciones());
    }
}
