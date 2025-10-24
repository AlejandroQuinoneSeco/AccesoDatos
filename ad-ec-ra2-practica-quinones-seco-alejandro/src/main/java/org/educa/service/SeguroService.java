package org.educa.service;

import org.educa.dao.SeguroDAOImpl;
import org.educa.entity.SeguroEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para la gestión de seguros de alquiler.
 * Proporciona funcionalidades para consultar información de seguros y sus precios.
 */
public class SeguroService {
    private final SeguroDAOImpl seguroDAO = new SeguroDAOImpl();


    /**
     * Obtiene todos los seguros disponibles en el sistema.
     *
     * @return una lista con todas las entidades SeguroEntity disponibles,
     *         ordenadas por identificador
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<SeguroEntity> findAll() throws SQLException {
        return seguroDAO.findAll();
    }

    /**
     * Busca un seguro por su identificador único.
     *
     * @param idSeguro el identificador único del seguro a buscar
     * @return la entidad SeguroEntity encontrada, o null si no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public SeguroEntity findById(Integer idSeguro) throws SQLException {
        return seguroDAO.findById(idSeguro);
    }

    /**
     * Obtiene el precio por día de un seguro específico.
     *
     * @param seguro la entidad SeguroEntity de la cual obtener el precio
     * @return el precio por día del seguro como BigDecimal
     * @throws RuntimeException si ocurre un error al acceder a la base de datos
     */
    public BigDecimal getPrecioPorDia(SeguroEntity seguro) {
        try {
            return seguroDAO.getPrecioPorDia(seguro.getIdSeguro());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
