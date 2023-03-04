package com.besysoft.practica.repositories.database;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.exceptions.GeneroExistsException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GeneroRepositoryDB extends CrudRepository<Genero, Long> {

    Optional<Genero> findByNombre(String nombre) throws GeneroExistsException;
}
