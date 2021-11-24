package com.ingenieria.rest.controlador;

import com.ingenieria.rest.modelo.Nota;
import com.ingenieria.rest.repositorio.NotasRepositorio;
import com.ingenieria.rest.servicio.NotasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/nota")
public class ControladorNota {

    @Autowired
    NotasServicio notasServicio;

    @Autowired
    NotasRepositorio notasRepositorio;


    @PutMapping(value = "/agrega", produces = "application/json",
            consumes = "application/json")
    public Map<String, Object> agrega(@RequestBody Map<String, Object> response) {
        Nota nota = notasServicio.agrega((String) response.get("nota"), (String) response.get("autor"));
        Map<String, Object> resp = new HashMap<>();
        resp.put("data", nota);
        resp.put("status", "success");
        return resp;
    }


    @PostMapping("/todas")
    public Map<String, Object> obtenNotas() {
        List<Nota> notas = notasRepositorio.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", notas);
        response.put("status", "success");
        return response;
    }
}
