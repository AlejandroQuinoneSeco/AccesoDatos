package org.educa.service;

import org.educa.dao.AlquilerDAOImpl;
import org.educa.dao.SeguroDAOImpl;
import org.educa.dao.VehiculoDAOImpl;
import org.educa.entity.AlquilerEntity;
import org.educa.entity.ClienteEntity;
import org.educa.entity.VehiculoEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AlquilerService {
    private static final BigDecimal DESCUENTO = BigDecimal.valueOf(0.05);
    private static final int ALQUILERES_PARA_DESCUENTO = 10;

    private final AlquilerDAOImpl alquilerDAO = new AlquilerDAOImpl();
    private final VehiculoService vehiculoService = new VehiculoService();
    private final SeguroService seguroService = new SeguroService();

    public List<AlquilerEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        List<AlquilerEntity> alquileres = alquilerDAO.findByCliente(cliente.getIdCliente());

        VehiculoDAOImpl vehiculoDAO = new VehiculoDAOImpl();
        for (AlquilerEntity alquiler : alquileres){
            VehiculoEntity vehiculo = vehiculoDAO.findById(alquiler.getVehiculo().getIdVehiculo());
            alquiler.setVehiculo(vehiculo);
        }
        return alquileres;
    }

    public void save(AlquilerEntity alquiler) throws SQLException {
        alquilerDAO.save(alquiler);
    }

    public void calculatePrecio(AlquilerEntity alquiler, int numAlquileres) {
        long dias = ChronoUnit.DAYS.between(alquiler.getFechaIni(), alquiler.getFechaFin());

        BigDecimal precioVehiculo = vehiculoService.getPrecioPorDia(alquiler.getVehiculo());

        BigDecimal precioSeguro = seguroService.getPrecioPorDia(alquiler.getSeguro());

        BigDecimal precioBase = precioVehiculo.add(precioSeguro)
                .multiply(BigDecimal.valueOf(dias));

        BigDecimal precioFinal;
        if (numAlquileres > ALQUILERES_PARA_DESCUENTO) {
            BigDecimal descuento = precioBase.multiply(DESCUENTO);
            precioFinal = precioBase.subtract(descuento);
        } else {
            precioFinal = precioBase;
        }

        precioFinal = precioFinal.setScale(2, RoundingMode.HALF_UP);

        alquiler.setPrecio(precioFinal);
    }
}
