package org.educa.service;

import org.educa.dao.CategoriaDAOImpl;
import org.educa.entity.CategoriaEntity;

import java.sql.SQLException;

public class CategoriaService {

    private final CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();
    public CategoriaEntity findById(Integer idCategoria) throws SQLException {

        return categoriaDAO.findById(idCategoria);
    }
}
