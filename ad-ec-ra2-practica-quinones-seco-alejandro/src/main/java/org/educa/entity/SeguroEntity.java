package org.educa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguroEntity {
    private Integer idSeguro;
    private String nombre;
    private String descripcion;
}

