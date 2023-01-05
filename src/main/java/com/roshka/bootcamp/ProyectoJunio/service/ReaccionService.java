package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.CrearReaccionDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.ReaccionDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.model.Reaccion;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.ReaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReaccionService {
    @Autowired
    private ReaccionRepository reaccionRepository;

    public List<Reaccion> list() {
        return reaccionRepository.findAll();
    }

    public Optional<Reaccion> findById(Long id) {
        return reaccionRepository.findById(id);
    }

    public void guardarReaccionDTO(CrearReaccionDTO reaccionDTO) {
        Reaccion reaccion = new Reaccion();
        reaccion.setIcono(reaccionDTO.getIcono());
        reaccion.setNombre(reaccionDTO.getIcono());
        reaccionRepository.save(reaccion);
    }
}
