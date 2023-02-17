package com.besysoft.practica.repositories.database;

import com.besysoft.practica.entities.Personaje;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonajeRepositoryDB extends CrudRepository<Personaje,Long> {

    Optional<Personaje> findByNombre(String nombre);

    Iterable<Personaje>findByEdadBetween(int desde, int hasta)throws Exception;
}
