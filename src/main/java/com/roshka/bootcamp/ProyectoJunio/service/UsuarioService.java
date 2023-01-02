package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.ValidarCorreo.CorreoService;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Permiso;
import com.roshka.bootcamp.ProyectoJunio.model.Rol;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.UsuarioRepository;

import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CorreoService correoService;

    public void resetPassword(String newPass, String correo, String token) {
        Usuario usuario = usuarioRepository.findByEmail(correo);
        usuario.setPassword(newPass);
        usuarioRepository.save(usuario);
    }

    /*
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }
    */

    @Override
    public Usuario guardarDTO(UsuarioRegistroDTO registroDTO) {
        /* Trae un objeto con los datos para un Usuario desde el formulario registrar
        *  para guardarlo en la base de datos.
        * */

        // se genera tokenVerificacion
        String tokenVerificacion = DigestUtils.sha256Hex(registroDTO.getEmail()
                + new Date().toString()
                + DigestUtils.md5Hex(UUID.randomUUID().toString()));

        // por defecto el estado del usuario es pendiente
        String estado = "I";

        /* enviar mensaje al correo registrado en el formulario para activar la cuenta */
        // generacio de link de activacion
        String text = "localhost:8080/verificacion?token=" + tokenVerificacion +
                "&correo=" + registroDTO.getEmail();
        String text2 = "\"" + text + "\""; // para los href
        // parametros para el correo
        String name = registroDTO.getNombre() + " " + registroDTO.getApellido();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("text", text);
        parametros.put("name", name);
        parametros.put("text2", text2);
        // titulo del correo
        String titulo = "Correo de verificación Roshkagram";
        //enviar correo
        correoService.sendEmailWithHTML(registroDTO.getEmail(), titulo, parametros);

        /* genera un objeto del tipo Usuario para guardarlo en la
        *  base de datos
        * */
        Usuario usuario = new Usuario(registroDTO.getNombre(),
                registroDTO.getApellido(), registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()),
                estado, tokenVerificacion);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        /* Trae un objeto del tipo Usuario y lo guardar en la base de
        *  datos
        * */
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* Del formulario Login trae el username, luego busca en la base de datos para agregar como usuario
        *  en el Spring Security con su respectos roles(permisos).
        * */
        Usuario usuario = usuarioRepository.findByEmail(username);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password invalidos");
        }

        /* validar que la cuenta esté 'A' [activa] para loguearse */
        if(usuario.getEstado().equals("I")) {
            throw new UsernameNotFoundException("El usuario no está activo");
        }

        /* validar que tenga el permiso de conectarse */
        // ...

        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }
    @Override
    public List<Usuario> listarUsuarios() {
        /* retorna una lista con todos los usuarios */
        return usuarioRepository.findAll();
    }

    public Usuario existeUsuario(String email) {
        return usuarioRepository.findByEmail(email);
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Set<Rol> roles) {
        /* Mapea cada rol del usuario (lista de listas) a una lista para que Spring security reconozca como
         * un rol (permiso) en el sistema.
         * */
        Set<SimpleGrantedAuthority> permisos = new HashSet<>();

        System.out.println(">> Roles:");
        for(Rol rol : roles) {
            for(Permiso permiso : rol.getPermisos()) {
                permisos.add(new SimpleGrantedAuthority(permiso.getNombre()));
                System.out.println(permiso.getNombre());
            }
        }

        return permisos;

    }
}
