package org.educa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerEntity {
    private Integer idAlquiler;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private ClienteEntity cliente = new ClienteEntity();
    private VehiculoEntity vehiculo = new VehiculoEntity();
    private SeguroEntity seguro = new SeguroEntity();
    private BigDecimal precio;
}

