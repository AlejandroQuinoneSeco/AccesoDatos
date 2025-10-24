package org.educa.service;

import org.educa.dao.CategoriaDAOImpl;
import org.educa.entity.CategoriaEntity;

import java.sql.SQLException;

/**
 * Servicio de lógica de negocio para la gestión de categorías de vehículos.
 * Proporciona funcionalidades para consultar información de categorías.
 */
public class CategoriaService {

    private final CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

    /**
     * Busca una categoría por su identificador único.
     *
     * @param idCategoria el identificador único de la categoría a buscar
     * @return la entidad CategoriaEntity encontrada con toda su información,
     *         o null si la categoría no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public CategoriaEntity findById(Integer idCategoria) throws SQLException {

        return categoriaDAO.findById(idCategoria);
    }
}
