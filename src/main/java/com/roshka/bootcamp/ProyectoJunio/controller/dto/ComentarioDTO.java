package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class ComentarioDTO {
    private String descripcion;
    private Foto foto;
    private String nombreCompleto;
    private Usuario usuario;
    private String idFoto;
    private String idComentario;
    public ComentarioDTO(){

    }
    public ComentarioDTO (Comentario comentario){
        this.descripcion = comentario.getDescripcion();
        this.idComentario = String.valueOf(comentario.getId_comentario());
//        this.foto = comentario.getFoto();
        this.nombreCompleto = comentario.getComentarioUsuario().getNombre() + " " + comentario.getComentarioUsuario().getApellido();
        this.usuario = comentario.getComentarioUsuario();
        this.idFoto = String.valueOf(comentario.getFoto().getId_foto());
    }

}
