package com.ingenieria.rest.servicio.impl;

import com.ingenieria.rest.modelo.Nota;
import com.ingenieria.rest.repositorio.NotasRepositorio;
import com.ingenieria.rest.servicio.NotasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class NotasServicioImpl implements NotasServicio {

    @Autowired
    NotasRepositorio notasRepositorio;

    public Nota agrega(String texto, String autor) {
        Nota nota = new Nota();
        nota.setNota(texto);
        nota.setCreada(new Timestamp(System.currentTimeMillis()));
        nota.setAutor(autor);
        return notasRepositorio.save(nota);
    }

    public String formatoFecha(Timestamp t) {
        LocalDateTime localDateTime = t.toLocalDateTime();
        return localDateTime.getDayOfMonth() + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getYear();
    }
}
