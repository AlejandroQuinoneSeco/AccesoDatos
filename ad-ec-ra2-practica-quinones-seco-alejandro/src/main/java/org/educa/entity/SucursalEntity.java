package org.educa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalEntity {
    private int idSucursal;
    private String calle;
    private String ciudad;
    private String pais;
    private String codigoPostal;
}
