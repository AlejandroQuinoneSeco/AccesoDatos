package org.educa.dao;

import org.educa.entity.SeguroEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface SeguroDAO {
    public List<SeguroEntity> findAll() throws SQLException;

    public SeguroEntity findById (Integer idSeguro) throws SQLException;

    public BigDecimal getPrecioPorDia (Integer idSeguro) throws SQLException;
}
