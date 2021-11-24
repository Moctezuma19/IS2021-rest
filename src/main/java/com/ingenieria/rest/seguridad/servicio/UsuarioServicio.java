package com.ingenieria.rest.seguridad.servicio;

import com.ingenieria.rest.modelo.Usuario;

public interface UsuarioServicio {
    Usuario findOne(String username);
}
