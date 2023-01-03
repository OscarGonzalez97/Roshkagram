package com.roshka.bootcamp.ProyectoJunio.controller.repository;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findAllByAlbum(Album album);
    List<Foto> findRutaByAlbum (Album album);
}

