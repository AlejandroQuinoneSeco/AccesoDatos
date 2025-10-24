package org.educa.service;

import org.educa.dao.SeguroDAOImpl;
import org.educa.entity.SeguroEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class SeguroService {
    private final SeguroDAOImpl seguroDAO = new SeguroDAOImpl();

    public List<SeguroEntity> findAll() throws SQLException {
        return seguroDAO.findAll();
    }

    public SeguroEntity findById(Integer idSeguro) throws SQLException {
        return seguroDAO.findById(idSeguro);
    }

    public BigDecimal getPrecioPorDia(SeguroEntity seguro) {
        try {
            return seguroDAO.getPrecioPorDia(seguro.getIdSeguro());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
