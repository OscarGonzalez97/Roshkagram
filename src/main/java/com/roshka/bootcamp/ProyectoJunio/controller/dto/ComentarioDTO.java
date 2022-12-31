package com.roshka.bootcamp.ProyectoJunio.controller.dto;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;
@Data
public class ComentarioDTO {
    private String descripcion;
    private Foto foto;
    private String nombreCompleto;
    private Usuario usuario;
    private String idFoto;
    private String idComentario;
}
