package com.ingenieria.rest.servicio;

import com.ingenieria.rest.dto.UsuarioDto;
import com.ingenieria.rest.modelo.Usuario;

public interface UsuariosServicio {
    UsuarioDto obtenUsuario(String nombre, String clave);
    Usuario creaUsuario(String nombre, String clave);
}
