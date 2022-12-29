package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioServiceInterace {
    public List<Comentario> list();
    public Optional<Comentario> findById(Long id);
}
