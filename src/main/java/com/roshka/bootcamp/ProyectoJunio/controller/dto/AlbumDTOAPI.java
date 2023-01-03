package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import java.util.Date;

public interface AlbumDTOAPI {
    Long getId_album();
    String getDescripcion();

    String getTitulo();

    Date getFechaCreacion();

    Long getUsuario_id();
    String getNombre();
    String getApellido();
}
