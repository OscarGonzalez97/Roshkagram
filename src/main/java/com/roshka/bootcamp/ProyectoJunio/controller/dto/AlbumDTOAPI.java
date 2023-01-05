package com.roshka.bootcamp.ProyectoJunio.controller.dto;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.FotoRepository;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class AlbumDTOAPI {
    private String titulo;
    private Long idAlbum;
    private String fechaCreacion;
    private String nombreUsuario;
    private String categoria;
    private String ruta;
    private List<String> Fotos;

    public AlbumDTOAPI(Album album) {
        this.titulo = album.getTitulo();
        this.idAlbum = album.getId_album();
        this.fechaCreacion = getDate(album);
        this.nombreUsuario = album.getUsuario().getNombre() + " " + album.getUsuario().getApellido();
        this.categoria = album.getCategoria().getNombre();
        if (album.getFotos().stream().findAny().isPresent())
            this.ruta = album.getFotos().stream().findAny().get().getRuta();
        else
            this.ruta = null;
        if (album.getFotos().stream().map(Foto::getRuta).findAny().isPresent())
            this.Fotos = album.getFotos().stream().map(Foto::getRuta).collect(Collectors.toList());
        else
            this.Fotos = null;
    }
    public String getDate(Album album){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(album.getFechaCreacion());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day+"-"+month+"-"+year;
    }
}