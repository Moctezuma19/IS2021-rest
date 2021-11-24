package com.ingenieria.rest.controlador;

import com.ingenieria.rest.dto.LoginUserDto;
import com.ingenieria.rest.dto.UsuarioDto;
import com.ingenieria.rest.modelo.Usuario;
import com.ingenieria.rest.repositorio.UsuariosRepositorio;
import com.ingenieria.rest.seguridad.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.impl.DefaultClaims;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/autenticacion")
public class ControladorAutenticacion {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUserDto loginUser) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getNombre(),
                        loginUser.getClave()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario user = usuariosRepositorio.findFirstByNombre(loginUser.getNombre());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        final String token = jwtProvider.doGenerateToken(user.getNombre());
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(user.getIdUsuario());
        usuarioDto.setNombre(user.getNombre());
        usuarioDto.setToken(token);

        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request)
            throws AuthenticationException {

        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
        String token = jwtProvider.doGenerateRefreshToken(claims, claims.getSubject());

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", token);
        response.put("success", "true");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
