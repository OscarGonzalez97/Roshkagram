package com.roshka.bootcamp.ProyectoJunio.controller.repository;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoDTOAPI;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findAllByAlbum(Album album);

    public static final String query = "SELECT f.id_foto AS id_foto , f.ruta AS ruta, f.descripcion AS descripcion FROM foto f where f.id_foto = :id";
    @Query(value =  query , nativeQuery = true)
    FotoDTOAPI findByIdFotoDTOAPI(@Param("id") Long id);

}
