package com.roshka.bootcamp.ProyectoJunio.controller;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ComentarioControllerRest {

    @Autowired
    private ComentarioService comentarioService;
    @GetMapping(value="/api/comentario/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ComentarioDTO getComentarioJsonById(@PathVariable long id) throws Exception {

        Optional<Comentario> Comentario = comentarioService.findById(id);
        ComentarioDTO comentarioRetorno = null;

        if(Comentario.isPresent()){
            comentarioRetorno = new ComentarioDTO(Comentario.get());
        }
        return comentarioRetorno;
    }
}
