package com.ingenieria.rest.repositorio;

import com.ingenieria.rest.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuario, Integer> {
    Usuario findFirstByNombre(String nombre);

    Usuario findUsuarioByNombreAndClave(String nombre, String clave);

    Boolean existsUsuarioByNombre(String nombre);
}
