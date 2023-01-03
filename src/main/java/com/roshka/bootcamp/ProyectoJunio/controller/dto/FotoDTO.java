package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;



@Data
public class FotoDTO {
    private Long id_foto;
    private String ruta;
    private String descripcion;

    public FotoDTO(Long id_foto, String ruta, String descripcion) {
        this.id_foto = id_foto;
        this.ruta = ruta;
        this.descripcion = descripcion;
    }
}
