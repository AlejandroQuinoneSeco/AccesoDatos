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


/**
 * Servicio de lógica de negocio para la gestión de alquileres.
 * Proporciona funcionalidades para buscar, guardar y calcular precios de alquileres,
 * aplicando las reglas de negocio correspondientes como descuentos por fidelidad.
 */
public class AlquilerService {
    private static final BigDecimal DESCUENTO = BigDecimal.valueOf(0.05);
    private static final int ALQUILERES_PARA_DESCUENTO = 10;

    private final AlquilerDAOImpl alquilerDAO = new AlquilerDAOImpl();
    private final VehiculoService vehiculoService = new VehiculoService();
    private final SeguroService seguroService = new SeguroService();

    /**
     * Busca todos los alquileres realizados por un cliente específico.
     * Además de la información básica del alquiler, carga la información
     * completa del vehículo asociado a cada alquiler.
     *
     * @param cliente la entidad ClienteEntity con el ID del cliente
     * @return una lista de AlquilerEntity con toda la información de los alquileres,
     *         o una lista vacía si el cliente no tiene alquileres previos
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<AlquilerEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        List<AlquilerEntity> alquileres = alquilerDAO.findByCliente(cliente.getIdCliente());

        VehiculoDAOImpl vehiculoDAO = new VehiculoDAOImpl();
        for (AlquilerEntity alquiler : alquileres){
            VehiculoEntity vehiculo = vehiculoDAO.findById(alquiler.getVehiculo().getIdVehiculo());
            alquiler.setVehiculo(vehiculo);
        }
        return alquileres;
    }

    /**
     * Guarda un nuevo alquiler en el sistema.
     *
     * @param alquiler la entidad AlquilerEntity a guardar con todos los datos necesarios
     * @throws SQLException si ocurre un error al guardar en la base de datos
     */
    public void save(AlquilerEntity alquiler) throws SQLException {
        alquilerDAO.save(alquiler);
    }

    /**
     * Calcula el precio total de un alquiler aplicando todas las reglas de negocio.
     * El cálculo incluye:
     * - Precio del vehículo según su categoría por día
     * - Precio del seguro por día
     * - Número total de días del alquiler
     * - Descuento del 5% si el cliente tiene más de 10 alquileres previos
     *
     * El precio calculado se establece directamente en la entidad alquiler proporcionada.
     *
     * @param alquiler la entidad AlquilerEntity con las fechas, vehículo y seguro definidos
     * @param numAlquileres el número de alquileres previos del cliente para aplicar descuentos
     */
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
