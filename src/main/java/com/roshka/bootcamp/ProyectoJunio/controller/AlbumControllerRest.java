package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AlbumControllerRest {
    @Autowired
    private AlbumService albumService;

    @GetMapping(value="/album-json/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public AlbumDTO getAlbumJsonById(@PathVariable long id) throws Exception {

        Optional<Album> album = albumService.findById(id);
        AlbumDTO albumRetorno=null;

        if(album.isPresent()){
            albumRetorno = new AlbumDTO(album.get());
        }
        return albumRetorno;
    }
}
