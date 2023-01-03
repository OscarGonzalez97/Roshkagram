package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlbumFotoDTO {
    private final AlbumDTOAPI album;

    private final List<FotoDTOAPI> fotos;

    public AlbumFotoDTO(AlbumDTOAPI album, List<FotoDTOAPI> fotos) {
        this.album = album;
        this.fotos = fotos;
    }
}
