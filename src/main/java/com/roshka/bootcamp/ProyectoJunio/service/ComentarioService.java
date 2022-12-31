package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> list() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }

    public void guardarComentarioDTO(ComentarioDTO comentarioDTO) {
        Date fecha = new Date();
        Comentario comentario = new Comentario();
        comentario.setDescripcion(comentarioDTO.getDescripcion());
        comentario.setFechaPublicacion(fecha);
        comentario.setFoto(comentarioDTO.getFoto());
        comentario.setComentarioUsuario(comentarioDTO.getUsuario());
        comentarioRepository.save(comentario);
    }
    public void borrarComentarioDTO(ComentarioDTO comentarioDTO) {
        comentarioRepository.deleteById(Long.parseLong(comentarioDTO.getIdComentario()));
    }
}
