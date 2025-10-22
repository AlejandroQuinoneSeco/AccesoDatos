package org.educa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    private Integer idCliente;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String dni;
    private String telefono;
    private List<DireccionEntity> direcciones = new ArrayList<>();
}

