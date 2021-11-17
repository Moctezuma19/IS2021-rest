package com.ingenieria.rest.servicio;

import com.ingenieria.rest.modelo.Nota;

import java.sql.Timestamp;

public interface NotasServicio {
    public Nota agrega(String texto);
    public String formatoFecha(Timestamp t);
}
