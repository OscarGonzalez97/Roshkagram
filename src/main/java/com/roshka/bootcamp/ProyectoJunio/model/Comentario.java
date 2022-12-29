package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comentario;
    private String descripcion;
    private Date fechaPublicacion;

    @OneToMany(mappedBy = "comentario")
    private Set<ReaccionComentario> reaciones;


    /*@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;*/

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario comentarioUsuario;

    @ManyToOne
    @JoinColumn(name = "foto_id", referencedColumnName = "id_foto")
    private Foto foto;



}
