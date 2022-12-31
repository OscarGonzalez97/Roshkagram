package com.roshka.bootcamp.ProyectoJunio.controller.dto;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.LinkedList;
import java.util.List;
@Data
public class FotoDTO {
    private Long idFoto;
    private String ruta;
    private String descripcion;
    private List<usuarioComentario> comentarios_usuarios;

    public FotoDTO(Foto foto){
        this.idFoto = foto.getId_foto();
        this.ruta = foto.getRuta();
        this.descripcion = foto.getDescripcion();
        this.comentarios_usuarios = new LinkedList<>();
        List<Comentario> comentario = foto.getListaComentarios();
        for (Comentario c: comentario) {
            comentarios_usuarios.add(new usuarioComentario(c.getComentarioUsuario().getId_usuario(), c.getComentarioUsuario().getNombre(), c.getComentarioUsuario().getApellido(),
                    c.getDescripcion(), c.getId_comentario()));
        }
        if (this.comentarios_usuarios.isEmpty()) {
            this.comentarios_usuarios = null;}
        }
}
@Getter
@Setter
@Builder
class usuarioComentario{
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String descripcionComentario;
    private Long idComentario;
}