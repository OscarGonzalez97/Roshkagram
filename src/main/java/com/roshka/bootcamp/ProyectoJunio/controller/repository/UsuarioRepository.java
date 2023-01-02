package com.roshka.bootcamp.ProyectoJunio.controller.repository;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.roshka.bootcamp.ProyectoJunio.model.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByEmail(String email);

    @Query(value = "SELECT new com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioDTO(u.nombre, u.apellido) FROM Usuario u where u.id_usuario=:id")
    UsuarioDTO findAllUsuarioDTO(@Param("id") Long id);

}
