package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.*;
import com.roshka.bootcamp.ProyectoJunio.model.*;
import com.roshka.bootcamp.ProyectoJunio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FotoComentarioController {

    @Autowired
    private FotoService fotoService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReaccionService reaccionService;

    @Autowired
    private ReaccionFotoService reaccionFotoService;

    @GetMapping("/foto-comentario/{id}")
    public String getFotoComentarioById(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo, @PathVariable long id, Model model) throws Exception {
        Optional<Foto> foto = fotoService.findById(id);
        Optional<Album> album = albumService.findById(foto.get().getAlbum().getId_album());
        List<Foto> fotos = fotoService.getFotos(foto.get().getAlbum().getId_album());
        Long next=null;
        Long prev=null;
        for(int i = 0; i<fotos.size(); i++){
            if(id == fotos.get(i).getId_foto()){
                if(i < fotos.size()-1) {
                    next = fotos.get(i + 1).getId_foto();
                }
                if(i !=0){
                    prev = fotos.get(i - 1).getId_foto();
                }
            }
        }
        if (foto.isPresent()) {
            System.out.println(album);
            model.addAttribute("foto", foto.get());
            model.addAttribute("nroAlbum", foto.get().getAlbum().getId_album() );
            model.addAttribute("pageAnt", pageNo);
            model.addAttribute("comentarios", foto.get().getListaComentarios());
            model.addAttribute("id_Foto", id);
            model.addAttribute("titulo",foto.get().getAlbum().getTitulo());
            model.addAttribute("next",next);
            model.addAttribute("prev",prev);
            model.addAttribute("reacciones", reaccionService.list());
            /* -- ENVIO DE LOS EMOJIS A LOS COMENTARIOS -- */
            model.addAttribute("reaccionesFoto", reaccionFotoService.obtenerReaccionesFoto(id));

        }
        return "foto-comentario";
    }

    @ModelAttribute("comentario")
    public ComentarioDTO retornaUnComentario() {
        return new ComentarioDTO();
    }

    @ModelAttribute("reaccion")
    public ReaccionDTO retornaUnaReaccion() {
        return new ReaccionDTO();
    }

    @PostMapping("foto-comentario")
    public String saveComentario(@ModelAttribute("comentario") ComentarioDTO comentarioDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        Foto foto = new Foto();
        System.out.println(comentarioDTO.getIdFoto());
        foto.setId_foto(Long.parseLong(comentarioDTO.getIdFoto()));

        try {
            /* comentario. id_foto y id_usuario */
            //comentarioDTO.setIdUsuario(usuario.getId_usuario().toString());
            comentarioDTO.setFoto(foto);
            comentarioDTO.setUsuario(usuario);
            if (!(comentarioDTO.getDescripcion().isEmpty())){
                comentarioService.guardarComentarioDTO(comentarioDTO);
            }

        } catch (Exception e) {
            System.out.println("error");
        }
        return "redirect:/foto-comentario/" + comentarioDTO.getIdFoto();
    }
    @PostMapping("foto-comentario-borrar")
    public String deleteComentario(@ModelAttribute("borrarComentario") ComentarioDTO comentarioDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        Foto foto = new Foto();
        foto.setId_foto(Long.parseLong(comentarioDTO.getIdFoto()));
        try {
            comentarioDTO.setUsuario(usuario);
            comentarioService.borrarComentarioDTO(comentarioDTO);
        } catch (Exception e) {
            System.out.println("error");
        }

        return "redirect:/foto-comentario/" + comentarioDTO.getIdFoto();
    }

    @PostMapping("foto-comentario/{id}")
    public String agregarReaccion(@ModelAttribute("crearReaccion") CrearReaccionDTO crearReaccionDTO, @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo, @PathVariable long id, Model model) throws Exception {
        //para redireccionar en la pagina correcta
        Optional<Foto> foto = fotoService.findById(id);
        Optional<Album> album = albumService.findById(foto.get().getAlbum().getId_album());
        List<Foto> fotos = fotoService.getFotos(foto.get().getAlbum().getId_album());
        Long next = null;
        Long prev = null;
        for (int i = 0; i < fotos.size(); i++) {
            if (id == fotos.get(i).getId_foto()) {
                if (i < fotos.size() - 1) {
                    next = fotos.get(i + 1).getId_foto();
                }
                if (i != 0) {
                    prev = fotos.get(i - 1).getId_foto();
                }
            }
        }
        if (foto.isPresent()) {
            System.out.println(album);
            model.addAttribute("foto", foto.get());
            model.addAttribute("nroAlbum", foto.get().getAlbum().getId_album());
            model.addAttribute("pageAnt", pageNo);
            model.addAttribute("comentarios", foto.get().getListaComentarios());
            model.addAttribute("id_Foto", id);
            model.addAttribute("titulo", foto.get().getAlbum().getTitulo());
            model.addAttribute("next", next);
            model.addAttribute("prev", prev);
            model.addAttribute("reacciones", reaccionService.list());
            /* -- ENVIO DE LOS EMOJIS A LOS COMENTARIOS -- */
            model.addAttribute("reaccionesFoto", reaccionFotoService.obtenerReaccionesFoto(id));

        }
        //crear reaccion nueva
        try {
            if (!(crearReaccionDTO.getIcono().isEmpty())){
                reaccionService.guardarReaccionDTO(crearReaccionDTO);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return "redirect:/foto-comentario/"+id;
    }

}
