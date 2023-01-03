package com.roshka.bootcamp.ProyectoJunio.controller;

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

    @GetMapping
    public String passwordResetVerified(@RequestParam(required = true, name = "token") String token,
                                        @RequestParam (required = true, name="correo") String correo){
        if (usuarioService.getTokenByEmail(correo).equals(token))
            return "password_reset_verified";
        return "redirect:/login?err003";
    }

    @PostMapping
    public String updatePassword(@RequestParam String newPass,
                               @RequestParam String correo,
                               @RequestParam String token){
        usuarioService.resetPassword(newPass, correo, token);
        return "redirect:/login?reset";
    }



}
