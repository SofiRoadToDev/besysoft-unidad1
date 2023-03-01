package com.besysoft.practica.services.interfaces;

import com.besysoft.practica.entities.Personaje;

import java.util.Optional;

public interface PersonajeService {

    Iterable<Personaje>obtenerTodos() throws Exception;

    Optional<Personaje> buscarPorNombre(String nombre) throws Exception;

    Personaje crearPersonaje(Personaje personajeMem) throws Exception;

    Iterable<Personaje>buscarPorRangoEdad(int desde, int hasta)throws Exception;


    Personaje actualizaPersonaje(Personaje personaje,Long id)throws Exception;


    void borrarPersonaje( Long id);


}
