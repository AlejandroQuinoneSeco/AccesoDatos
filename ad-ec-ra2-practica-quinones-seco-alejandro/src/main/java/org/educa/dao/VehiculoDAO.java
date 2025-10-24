package org.educa.dao;

import org.educa.entity.VehiculoEntity;

import java.sql.SQLException;
import java.util.List;

public interface VehiculoDAO {

    public List<VehiculoEntity> findBySucursal(Integer idSucursal) throws SQLException;

    public VehiculoEntity findById (Integer idVehiculo) throws SQLException;

}
