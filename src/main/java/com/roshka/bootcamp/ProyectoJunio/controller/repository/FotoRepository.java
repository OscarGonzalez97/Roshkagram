package com.roshka.bootcamp.ProyectoJunio.controller.repository;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoDTO;
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

    @Query(value = "SELECT new com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoDTO(f.id_foto , f.ruta , f.descripcion) FROM Foto f where f.id_foto=:id")
    FotoDTO findByIdFotoDTO(@Param("id") Long id);

}
