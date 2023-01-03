package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumFotoDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTOAPI;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoDTOAPI;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.AlbumRepository;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/API")
public class AlbumControllerRest {
    @Autowired
    FotoRepository fotoRepository;
    @Autowired
    AlbumRepository albumRepository;

    @GetMapping(value="/AlbumFoto/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public AlbumFotoDTO getAlbumJsonById(@PathVariable long id) throws Exception {
        AlbumDTOAPI album = null;
        List<FotoDTOAPI> fotos = null;
        try{
            fotos = fotoRepository.findAllFotoDTOAPI(id);
            album = albumRepository.findAlbumDTOAPI(id);

            if(album != null){
                return new AlbumFotoDTO(album, fotos);
            }
        }catch (Exception e){
            System.err.println(e.getClass().getName() + e.getStackTrace());
            return null;
        }

        return null;
    }
}
