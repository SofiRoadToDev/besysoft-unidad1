package com.besysoft.practica.repositories.interfaces;

import com.besysoft.practica.dominio.Personaje;

public interface PersonajeRepository {
    Iterable<Personaje> getAllFromSampleData();
   
    Personaje getById() throws Exception;
    Iterable<Personaje>getByAgeRange(int desde,int hasta);
    Personaje getByName(String name) throws Exception;

    void createPersonaje(Personaje personaje) throws Exception;

    void updatePersonaje(Personaje personaje)throws Exception;
}
