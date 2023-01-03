package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class FotoComentarioDTO {

    private final FotoDTOAPI foto;
    private final List<ComentarioDTOAPI> comentarios;


    public FotoComentarioDTO(FotoDTOAPI foto, List<ComentarioDTOAPI> comentarios) {
        this.foto = foto;
        this.comentarios = comentarios;
    }
}
