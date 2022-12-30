package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
public class AlbumDTOAPI {
    private String titulo;
    private Long idAlbum;
    private Date fechaCreacion;
    private String nombreUsuario;
    private String categoria;

    public AlbumDTOAPI(Album album) {
        this.titulo = album.getTitulo();
        this.idAlbum = album.getId_album();
        this.fechaCreacion = album.getFechaCreacion();
        this.nombreUsuario = album.getUsuario().getNombre() + " " + album.getUsuario().getApellido();
        this.categoria = album.getCategoria().getNombre();
}
@Builder
class albumFoto{
    private String ruta;
}