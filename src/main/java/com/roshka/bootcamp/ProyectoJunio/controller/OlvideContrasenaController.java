package com.roshka.bootcamp.ProyectoJunio.controller;
import com.roshka.bootcamp.ProyectoJunio.ValidarCorreo.CorreoService;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String mostrarFomrularioDeRegistro() {
        return "password_reset";
    }
    @GetMapping ("/password_reset")
    public String validarCorreo(@RequestParam(defaultValue = "") String token, @RequestParam(defaultValue = "") String correo) {
        Usuario usuario = usuarioService.existeUsuario(correo);
        if (correo.equals("") || token.equals(""))
            //Si el token o correo esta vacio
            return "redirect:/password_reset?err001";
        //Si los token son distintos
        if (!token.equals(usuario.getReset_password_token())) {
            System.out.println("hola");
            return "redirect:/password_reset?err001";
        }
        System.out.println("hola");
        return "redirect:/password_reset/verified";
    }
    @GetMapping("/password_reset/verified")
    public String changePassowrd(@RequestParam (required = false) String token, @RequestParam(defaultValue = "") String correo){
        Usuario usuario = usuarioService.existeUsuario(correo);
        return token+ " " + correo;
    };
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
