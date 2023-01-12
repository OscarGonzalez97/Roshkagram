package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FotoComentarioControllerRest {
    @Autowired
    private FotoService fotoService;
    @GetMapping(value="/api/foto_comentario/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoDTO getFotoJsonById(@PathVariable long id) throws Exception {

        Optional<Foto> foto = fotoService.findById(id);
        FotoDTO fotoRetorno=null;

        if(foto.isPresent()){
            fotoRetorno = new FotoDTO(foto.get());
        }
        return fotoRetorno;
    }


}
