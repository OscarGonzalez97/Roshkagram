package com.roshka.bootcamp.ProyectoJunio.model;

import java.util.Set;
import java.util.HashSet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name="estado")
    private String estado;

    private String email;

    private String password;

    @Column(name = "token_verificacion")
    private String tokenVerificacion;

    @Column(name = "reset_password_token")
    private String reset_password_token;
    public Usuario() {

    }

    public Usuario(String nombre, String apellido, String email, String password, String reset_password_token) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.reset_password_token = reset_password_token;
    }

    public Usuario(String nombre, String apellido, String email, String password, String estado, String tokenVerificacion) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.tokenVerificacion = tokenVerificacion;
    }

    /** RELACIONES PARA LA BASE DE DATOS **/
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = {@JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "rol_id")}
    )
    private Set<Rol> roles = new HashSet<>();

    /*// Relaciona que a un usuario le pertenece una reaccion de una foto
    @OneToOne(mappedBy = "usuario")
    private ReaccionFoto reaccionFoto;

    // Relaciona que a un usuario le pertenece una reaccion de un comentario
    @OneToOne(mappedBy = "usuario")
    private ReaccionComentario reaccionComentario;

    // Un usuario puede tener varios comentarios en una foto
    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> comentario = new HashSet<>();

    // Un usuario puede tener varios albums
    @OneToMany(mappedBy = "usuario")
    private Set<Album> albums = new HashSet<>();*/



}
