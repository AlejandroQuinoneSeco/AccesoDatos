package org.educa.service;

import org.educa.dao.CategoriaDAOImpl;
import org.educa.dao.VehiculoDAOImpl;
import org.educa.entity.SucursalEntity;
import org.educa.entity.VehiculoEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


/**
 * Servicio de lógica de negocio para la gestión de vehículos.
 * Proporciona funcionalidades para consultar vehículos, sus precios
 * y disponibilidad en sucursales.
 */
public class VehiculoService {
    private final VehiculoDAOImpl vehiculoDAO = new VehiculoDAOImpl();
    private final CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

    /**
     * Busca todos los vehículos disponibles en una sucursal específica.
     * Los vehículos incluyen información completa de su categoría y sucursal.
     *
     * @param sucursal la entidad SucursalEntity de la cual obtener los vehículos
     * @return una lista de VehiculoEntity disponibles en la sucursal,
     *         ordenados por marca y modelo
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<VehiculoEntity> findBySucursal(SucursalEntity sucursal) throws SQLException {
        return vehiculoDAO.findBySucursal(sucursal.getIdSucursal());
    }

    /**
     * Obtiene el precio por día de un vehículo según su categoría.
     *
     * @param vehiculo la entidad VehiculoEntity de la cual obtener el precio
     * @return el precio por día del vehículo como BigDecimal,
     *         basado en su categoría
     * @throws RuntimeException si ocurre un error al acceder a la base de datos
     */
    public BigDecimal getPrecioPorDia(VehiculoEntity vehiculo) {
        try {
            return categoriaDAO.getPrecioPorDia(vehiculo.getCategoria().getIdCategoria());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Busca un vehículo por su identificador único.
     * El vehículo incluye información completa de su categoría y sucursal.
     *
     * @param idVehiculo el identificador único del vehículo a buscar
     * @return la entidad VehiculoEntity encontrada con toda su información,
     *         o null si no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public VehiculoEntity findById(Integer idVehiculo) throws SQLException {
        return vehiculoDAO.findById(idVehiculo);
    }
}
