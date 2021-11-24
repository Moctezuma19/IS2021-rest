package com.ingenieria.rest.servicio.impl;

import com.ingenieria.rest.dto.UsuarioDto;
import com.ingenieria.rest.modelo.Usuario;
import com.ingenieria.rest.repositorio.UsuariosRepositorio;
import com.ingenieria.rest.servicio.UsuariosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServicioImpl implements UsuariosServicio {

    @Autowired
    UsuariosRepositorio usuariosRepositorio;

    @Override
    public UsuarioDto obtenUsuario(String nombre, String clave) {
        Usuario usuario = usuariosRepositorio.findUsuarioByNombreAndClave(nombre, new BCryptPasswordEncoder().encode(clave));
        if (usuario == null) {
            return null;
        }
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNombre(usuario.getNombre());
        return usuarioDto;
    }

    @Override
    public Usuario creaUsuario(String nombre, String clave) {
        if(usuariosRepositorio.existsUsuarioByNombre(nombre)){
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setClave(new BCryptPasswordEncoder().encode(clave));

        return usuariosRepositorio.saveAndFlush(usuario);
    }
}
