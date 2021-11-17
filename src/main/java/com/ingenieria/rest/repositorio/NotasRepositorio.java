package com.ingenieria.rest.repositorio;

import com.ingenieria.rest.modelo.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasRepositorio extends JpaRepository<Nota, Integer> {
}
