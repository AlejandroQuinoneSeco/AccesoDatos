package org.educa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionEntity {
    private Integer idDireccion;
    private String calle;
    private String ciudad;
    private String pais;
    private String cp;
    private Integer idCliente;
}
