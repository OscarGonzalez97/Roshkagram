package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;

@Data
public class ComentarioDTOAPI {
    private String descripcion;
    private Long usuario_id;
    private String idComentario;
    public ComentarioDTOAPI(String descripcion, Long usuario_id, String idComentario) {
        this.descripcion = descripcion;
        this.usuario_id  = usuario_id;
        this.idComentario = idComentario;
    }
}
