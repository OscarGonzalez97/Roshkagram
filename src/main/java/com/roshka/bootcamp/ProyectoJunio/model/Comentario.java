package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Long id_comentario;
    private String descripcion;
    private Date fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "foto_id", referencedColumnName = "id_foto")
    private Foto foto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "comentario")
    private Set<ReaccionComentario> reaciones;

}