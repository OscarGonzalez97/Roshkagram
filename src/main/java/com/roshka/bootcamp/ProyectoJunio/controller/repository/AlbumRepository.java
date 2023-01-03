package com.roshka.bootcamp.ProyectoJunio.controller.repository;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTOAPI;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    Page<Album> findByCategoria(Optional<Categoria> categoria, Pageable pageable);

    static final String query="SELECT a.id_album AS id_album, a.descripcion AS descripcion, a.titulo AS titulo," +
            " a.usuario_id AS usuario_id, u.nombre AS nombre, u.apellido AS apellido FROM album a INNER JOIN usuario u ON a.usuario_id = u.id_usuario WHERE id_album = :id";
    @Query(value = query,nativeQuery = true)
    AlbumDTOAPI findAlbumDTOAPI(@Param("id") Long id);
}
