package com.besysoft.practica.repositories.memory.interfaces;

import com.besysoft.practica.dominio.PersonajeMem;

import java.util.Optional;

public interface PersonajeRepository {
    Iterable<PersonajeMem> getAllFromSampleData();
   
    Optional<PersonajeMem> getById(int id) ;
    Iterable<PersonajeMem>getByAgeRange(int desde, int hasta);
    Optional<PersonajeMem> getByName(String name);

    PersonajeMem createPersonaje(PersonajeMem personajeMem) ;

    PersonajeMem updatePersonaje(PersonajeMem personajeMem, int id);
}
