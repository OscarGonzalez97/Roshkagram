package com.roshka.bootcamp.ProyectoJunio;

import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/password_reset_verified")
public class resetPasswordController {

    private final UsuarioService usuarioService;

    @Autowired
    public resetPasswordController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public String passwordResetVerified(@RequestParam(required = false, name = "token") String token,
                                        @RequestParam (required = false, name="correo") String correo){
        if (token != null )
            return "password_reset_verified";
        return "/login";
    }

    @PutMapping
    public void updatePassword(@RequestParam String newPass,
                               @RequestParam String correo,
                               @RequestParam String token){
        usuarioService.resetPassword(newPass, correo, token);
    }



}
