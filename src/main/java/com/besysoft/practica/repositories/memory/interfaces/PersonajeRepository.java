package com.besysoft.practica.repositories.memory.interfaces;

import com.besysoft.practica.dominio.Personaje;

import java.util.Optional;

public interface PersonajeRepository {
    Iterable<Personaje> getAllFromSampleData();
   
    Optional<Personaje> getById(int id) ;
    Iterable<Personaje>getByAgeRange(int desde,int hasta);
    Optional<Personaje> getByName(String name);

    Personaje createPersonaje(Personaje personaje) ;

    Personaje updatePersonaje(Personaje personaje, int id);
}
