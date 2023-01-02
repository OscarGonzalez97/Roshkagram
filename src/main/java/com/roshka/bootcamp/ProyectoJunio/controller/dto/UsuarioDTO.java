package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class UsuarioDTO {

    private String nombre;

    private String apellido;

    public UsuarioDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
