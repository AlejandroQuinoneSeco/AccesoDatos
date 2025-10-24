package org.educa.dao;

import org.educa.entity.CategoriaEntity;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface CategoriaDAO {
    public CategoriaEntity findById (Integer idCategoria) throws SQLException;

    public BigDecimal getPrecioPorDia (Integer idCategoria) throws SQLException;
}
