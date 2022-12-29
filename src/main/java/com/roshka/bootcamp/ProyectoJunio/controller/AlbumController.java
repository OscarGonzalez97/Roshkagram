package com.roshka.bootcamp.ProyectoJunio.controller;


import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
/*import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;*/
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private FotoService fotoService;

    @Autowired
    private UsuarioService usuarioService;

    public AlbumController (AlbumService albumService) {this.albumService = albumService;}


    @GetMapping("/album/{id}")
    public String getAlbumById(@PathVariable long id, @RequestParam(name="pageAnt", required=false,defaultValue= "0") int pageAnt,Model model) throws Exception {
        Optional<Album> album = albumService.findById(id);
        List<Foto> fotos = fotoService.getFotos(id);
        if(album.isPresent()){
            model.addAttribute("titulo", album.get().getTitulo());
            model.addAttribute("descripcion", album.get().getDescripcion());
            model.addAttribute("fotos", fotos);
            model.addAttribute("pageAnt", pageAnt);
            model.addAttribute("id", album.get().getId_album());
        }
        return "album-fotos";
    }

    @PostMapping("/creacion-album-foto/{idAlbum}")
    public String postFormFoto(@RequestParam("file") MultipartFile file){

////        if(!file.isEmpty()){
////            Path directorioImagenes= Paths.get("src/main/resources/static/img/ImagenesPrueba");
////            String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();
////            try {
////                byte[] bytesImg=file.getBytes();
////                Path rutaCompleta=Paths.get(rutaAbsoluta+"/"+file.getOriginalFilename());
////                Files.write(rutaCompleta,bytesImg);
////            } catch (IOException e) {
////                throw new RuntimeException(e);
////            }
////        }
        return "formulario-fotos";
    }
    @PostMapping("foto-borrar")
    public String eliminarFoto(@RequestParam("idFoto") long idFoto, @RequestParam("idAlbum") long album_id){    //borrar archivo en el servidor
        //borrar archivo del servidor
        try{
            String ruta = fotoService.findById(idFoto).get().getRuta();
            Path directorioImg = Paths.get("src\\main\\resources\\static\\"+ruta);
            String rutaCompleta = directorioImg.toFile().getAbsolutePath();
            Files.delete(Paths.get(rutaCompleta));
            System.out.println(Paths.get(rutaCompleta)+"-------------------------------------------------------- EXITOSO");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        //borrar referencia a la base de datos
        fotoService.delete(idFoto);
        return "redirect:/album/" + album_id;
    }
}