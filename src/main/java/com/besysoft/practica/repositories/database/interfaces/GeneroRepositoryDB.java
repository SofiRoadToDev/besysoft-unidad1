package com.besysoft.practica.repositories.database.interfaces;

import com.besysoft.practica.entities.Genero;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GeneroRepositoryDB extends CrudRepository<Genero, Long> {

    Optional<Genero> findByNombre(String nombre) throws Exception;
}
