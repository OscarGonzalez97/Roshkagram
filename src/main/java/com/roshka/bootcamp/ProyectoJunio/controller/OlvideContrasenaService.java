package com.roshka.bootcamp.ProyectoJunio.controller;
import com.roshka.bootcamp.ProyectoJunio.ValidarCorreo.CorreoService;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import freemarker.template.Configuration;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class OlvideContrasenaService {
    @Autowired
    private static JavaMailSender mailSender;
    @Autowired
    CorreoService correoService;
    @Autowired
    private Configuration config;
    @Autowired
    private UsuarioService usuarioService;
    private static final String from = "roshkagram@roshka.com";

    public void sendEmail(Usuario usuario, String correo) {
        String tokenVerificacion = DigestUtils.sha256Hex(usuario.getEmail()
                + new Date().toString()
                + DigestUtils.md5Hex(UUID.randomUUID().toString()));
        String text = "https://localhost:8080/password_reset/verified?token=" + tokenVerificacion +
                "&correo=" + usuario.getEmail();
        String text2 = "\"" + text + "\""; // para los href
        String name = usuario.getNombre() + " " + usuario.getApellido();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("text", text);
        parametros.put("name", name);
        parametros.put("text2", text2);
        String titulo = "Restablecer contraseña de Roshkagram";
        correoService.passwordReset(usuario.getEmail(), titulo, parametros);
    }
}
