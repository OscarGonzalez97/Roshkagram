package com.roshka.bootcamp.ProyectoJunio.controller.repository;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTOAPI;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{


    public static final String query = "SELECT c.descripcion AS descripcion , c.usuario_id AS usuario_id , c.id_comentario AS id_comentario , u.nombre AS usuarioNombre, u.apellido AS usuarioApellido" +
            " FROM Comentario c INNER JOIN Usuario u ON c.usuario_id = u.id_usuario where foto_id = :id";

    @Query(value = query, nativeQuery = true)
    List<ComentarioDTOAPI> findAllComentarioDTOAPIById(@Param("id") Long id);

}
