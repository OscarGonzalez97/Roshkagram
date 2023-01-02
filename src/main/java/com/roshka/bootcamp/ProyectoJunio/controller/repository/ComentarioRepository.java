package com.roshka.bootcamp.ProyectoJunio.controller.repository;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTOAPI;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{


    public static final String query = "SELECT decripcion , usuario_id , id_comentario FROM Comentario where foto_id = :id";
    @Query(value = query, nativeQuery = true)
    List<ComentarioDTOAPI> findAllComentarioDTOAPIById(@Param("id") Long id);

}
