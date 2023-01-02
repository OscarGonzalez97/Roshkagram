package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;


public interface ComentarioDTOAPI {
    String getDescripcion();
    String getId_comentario();
    Long getUsuario_id();
    String getUsuarioNombre();
    String getUsuarioApellido();


}
