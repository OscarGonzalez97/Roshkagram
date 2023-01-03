package com.roshka.bootcamp.ProyectoJunio.controller;




import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTOAPI;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.ComentarioRepository;
import com.roshka.bootcamp.ProyectoJunio.service.ComentarioService;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoDTOAPI;

import java.util.List;

@RestController
public class FotoComentarioControllerRest{

    @Autowired
    public FotoService foto;
    @Autowired
    public ComentarioService comentario;
    @Autowired
    public UsuarioService usuario;
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping(value="/api/fotoComentario/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoComentarioDTO getAlbumJsonById(@PathVariable long id) throws Exception {
        List<ComentarioDTOAPI> comentarios= null;
        FotoDTOAPI fotoTarget = null;
        try{
            fotoTarget = foto.fotoRepository.findByIdFotoDTOAPI(id);
            comentarios = comentario.comentarioRepository.findAllComentarioDTOAPIById(id);

            if(fotoTarget != null){
                return new FotoComentarioDTO(fotoTarget, comentarios);
            }

            System.out.println(comentarios.get(0).getDescripcion());
        }catch (Exception e){
            System.err.println(e.getClass().getName() + e.getStackTrace());
            return null;
        }


        return null;
    }

}
