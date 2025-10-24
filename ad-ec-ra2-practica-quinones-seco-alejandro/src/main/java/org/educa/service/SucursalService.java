package org.educa.service;

import org.educa.dao.SucursalDAOImpl;
import org.educa.entity.SucursalEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para la gestión de sucursales.
 * Proporciona funcionalidades para consultar información de sucursales disponibles.
 */
public class SucursalService {
    private final SucursalDAOImpl sucursalDAO = new SucursalDAOImpl();

    /**
     * Obtiene todas las sucursales registradas en el sistema.
     *
     * @return una lista con todas las entidades SucursalEntity disponibles,
     *         ordenadas por ciudad y calle
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<SucursalEntity> findAll() throws SQLException {
        return sucursalDAO.findAll();
    }
}
