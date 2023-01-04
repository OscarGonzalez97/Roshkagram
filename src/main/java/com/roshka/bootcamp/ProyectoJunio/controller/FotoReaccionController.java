package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ReaccionDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Reaccion;
import com.roshka.bootcamp.ProyectoJunio.model.ReaccionFoto;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import com.roshka.bootcamp.ProyectoJunio.service.ReaccionFotoService;
import com.roshka.bootcamp.ProyectoJunio.service.ReaccionService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FotoReaccionController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ReaccionService reaccionService;

    @Autowired
    FotoService fotoService;

    @Autowired
    ReaccionFotoService reaccionFotoService;

    @PostMapping("foto-reaccion")
    public String saveFotoReaccion(@ModelAttribute("reaccion")ReaccionDTO reaccionDTO, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = usuarioService.existeUsuario(username);
        Optional<Foto> foto = fotoService.findById(Long.parseLong(reaccionDTO.getFoto()));
        Optional<Reaccion> reaccion = reaccionService.findById(Long.parseLong(reaccionDTO.getReaccion()));

        if( !foto.isPresent() || !reaccion.isPresent()) {
            return "redirect:/error";
        }

        Foto foto1 = foto.get();
        Reaccion reaccion1 = reaccion.get();

        ReaccionFoto reaccionFoto = new ReaccionFoto();
        reaccionFoto.setReaccion(reaccion1);
        reaccionFoto.setFoto(foto1);
        reaccionFoto.setUsuario(usuario);

        try {
            if(reaccion.isPresent()) {
                reaccionFotoService.guardar(reaccionFoto);  //guarda la reaccion en la foto
            }else
                reaccionFotoService.delete(reaccionFoto);


            /* -- ENVIO DE LOS EMOJIS A LOS COMENTARIOS -- */
            model.addAttribute("reaccionesFoto", reaccionFotoService.obtenerReaccionesFoto(Long.parseLong(reaccionDTO.getFoto())));

            return "redirect:/foto-comentario/" + reaccionDTO.getFoto() + "?pageAnt=" + reaccionDTO.getPageAnt();
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

}
