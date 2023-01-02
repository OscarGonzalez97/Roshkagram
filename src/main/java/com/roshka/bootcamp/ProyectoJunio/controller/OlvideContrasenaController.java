package com.roshka.bootcamp.ProyectoJunio.controller;
import com.roshka.bootcamp.ProyectoJunio.ValidarCorreo.CorreoService;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.OlvideContrasenaService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/password_reset")
public class OlvideContrasenaController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CorreoService correoService;
    @Autowired
    private OlvideContrasenaService olvideContrasenaService;
    @Autowired

    @GetMapping
    public String mostrarPasswordReset() {
        return "password_reset";
    }

    @GetMapping("/verified")
    public String validarCorreo(@RequestParam (required = false, name = "token") String token,
                                @RequestParam (required = false, name="correo") String correo) {
        Usuario usuario = usuarioService.existeUsuario(correo);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (correo.equals("") || token.equals("")) {
            //Si el token o correo esta vacio
            return "redirect:/password_reset?err777";
        }
        //Si los token son distintos
        if (!token.equals(usuario.getReset_password_token())) {
            return "redirect:/password_reset?err888";
        }
            return "redirect:/password_reset_verified?token="+token+"?correo="+correo;
    }
    /*@RequestMapping("/password_reset/verified")
    public String changePassowrd(@RequestParam (required = false) String token, @RequestParam(defaultValue = "") String correo){
        Usuario usuario = usuarioService.existeUsuario(correo);
        return "password_reset/verified";
    };*/
    @PostMapping
    public String passwordReset(@RequestParam(name="correo") String correo) {
        try {
            Usuario usuario = usuarioService.existeUsuario(correo);
            if (usuario.getEmail() != null) {
                //Verificar que sea un correo que ya existe en la base de datos
                olvideContrasenaService.sendEmail(usuario, correo);
                return "redirect:/password_reset?exito";
            }
            else {
                return "redirect:/password_reset?exito";
            }
        } catch (Exception e) {
            return "redirect:/password_reset?exito";
        }
    }
}
