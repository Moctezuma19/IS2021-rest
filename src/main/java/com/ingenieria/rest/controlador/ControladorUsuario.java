package com.ingenieria.rest.controlador;

import com.ingenieria.rest.modelo.Usuario;
import com.ingenieria.rest.servicio.UsuariosServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {

    private final static Logger logger = LoggerFactory.getLogger(ControladorUsuario.class);

    @Autowired
    UsuariosServicio usuariosServicio;

    @PutMapping("/agrega")
    public ResponseEntity<?> agregaUsuario(@RequestBody Map<String, String> request) {
        logger.info("nuevo usuario: " + request);
        Usuario usuario = usuariosServicio.creaUsuario(request.get("nombre"), request.get("clave"));
        if (usuario != null) {
            usuario.setClave(null);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
