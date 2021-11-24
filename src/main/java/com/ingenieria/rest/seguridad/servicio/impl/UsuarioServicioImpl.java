package com.ingenieria.rest.seguridad.servicio.impl;

import com.ingenieria.rest.modelo.Usuario;
import com.ingenieria.rest.repositorio.UsuariosRepositorio;
import com.ingenieria.rest.seguridad.modelo.UsuarioPrincipal;
import com.ingenieria.rest.seguridad.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio, UserDetailsService {

    @Autowired
    UsuariosRepositorio usuariosRepositorio;


    @Override
    public UsuarioPrincipal loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuariosRepositorio.findFirstByNombre(nombre);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado!");
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        List<GrantedAuthority> listAuth = new ArrayList<>();
        listAuth.add(grantedAuthority);

        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(usuario.getNombre(), usuario.getClave(), listAuth);
        usuarioPrincipal.setIdUser(usuario.getIdUsuario());
        return usuarioPrincipal;
    }

    @Override
    public Usuario findOne(String username) {
        return usuariosRepositorio.findFirstByNombre(username);
    }
}
