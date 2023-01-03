package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTOeditar;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Categoria;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import com.roshka.bootcamp.ProyectoJunio.service.CategoriaService;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class ControladorCrearAlbum {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FotoService fotoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("mostrarVista")
    public String mostrarVista(){
        return "PruebaCrearAlbum";
    }

    @PostMapping("/crearAlbum")
    public String crearAlbum(@ModelAttribute() AlbumDTO albumDTO){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        albumDTO.setUsuario(usuario);
        Album albumGuardado = albumService.guardar(albumDTO);
        albumDTO.setId_album(albumGuardado.getId_album());
        System.out.println("Creamos un album exitosamente");
        return "redirect:/album/" + albumGuardado.getId_album() + "/subir-fotos";
    }

    @GetMapping("/album/{id}/subir-fotos")
    public String subirFotos(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("idAlbum", id);
        Optional<Album> albumActual = albumService.findById(id);
        if (albumActual.isPresent()) {
            model.addAttribute("album", albumActual.get());
        }
        return "DropzoneFoto";
    }

    @PostMapping("/album/{id}/subir-fotos")
    public String postFormFoto(@RequestParam("file") MultipartFile file, @PathVariable long id, Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Album> albumActual = albumService.findById(id);
        model.addAttribute("idAlbum", id);
        if (albumActual.isPresent()) {
            if(!file.isEmpty()){
                Path directorioImagenes= Paths.get("images/"+ DigestUtils.md5Hex(file.getOriginalFilename()));
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                try {
                    byte[] bytesImg=file.getBytes();
                    Path rutaCompleta=Paths.get(rutaAbsoluta);
                    Files.write(rutaCompleta, bytesImg);
//                     si todo salio bien guardamos la foto en la base de datos
                    fotoService.guardarFoto(albumActual.get(),"images/"+ DigestUtils.md5Hex(file.getOriginalFilename()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "redirect:/album/" + id ;
    }

    @PostMapping("editar-album/{id}")
    public String editarAlbum(@PathVariable long id, @ModelAttribute("editar") AlbumDTOeditar editar) throws Exception {
        System.out.println("hola, estamos en el editar");
        Optional<Album> albumActual = albumService.findById(id);
        Date date_auxiliar;
        Album albumAuxilar=albumActual.get();
        albumAuxilar.setTitulo(editar.getTitulo2());
        albumAuxilar.setDescripcion(editar.getDescripcion2());
        Optional<Categoria> cat=categoriaService.findById(editar.getIdCategoria2());
        albumAuxilar.setCategoria(cat.get());
        try {
            date_auxiliar=new SimpleDateFormat("dd/MM/yyyy").parse(editar.getFechaEvento2());
        } catch (ParseException e) {
            date_auxiliar=new Date();
        }
        albumAuxilar.setFechaEvento(date_auxiliar);
        Album albumEditado=albumService.editar(albumAuxilar);
        return "redirect:/album/" + id ;
    }


}
