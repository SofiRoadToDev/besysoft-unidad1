package com.besysoft.practica.repositories.database.interfaces;

import com.besysoft.practica.entities.Personaje;
import org.springframework.data.repository.CrudRepository;

public interface PersonajeRepositoryDB extends CrudRepository<Personaje,Long> {
}
