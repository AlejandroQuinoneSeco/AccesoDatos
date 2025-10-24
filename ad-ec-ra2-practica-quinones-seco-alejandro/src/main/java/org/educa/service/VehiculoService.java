package org.educa.service;

import org.educa.dao.CategoriaDAOImpl;
import org.educa.dao.VehiculoDAOImpl;
import org.educa.entity.SucursalEntity;
import org.educa.entity.VehiculoEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class VehiculoService {
    private final VehiculoDAOImpl vehiculoDAO = new VehiculoDAOImpl();
    private final CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

    public List<VehiculoEntity> findBySucursal(SucursalEntity sucursal) throws SQLException {
        return vehiculoDAO.findBySucursal(sucursal.getIdSucursal());
    }

    public BigDecimal getPrecioPorDia(VehiculoEntity vehiculo) {
        try {
            return categoriaDAO.getPrecioPorDia(vehiculo.getCategoria().getIdCategoria());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public VehiculoEntity findById(Integer idVehiculo) throws SQLException {
        return vehiculoDAO.findById(idVehiculo);
    }
}
