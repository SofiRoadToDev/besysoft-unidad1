package com.besysoft.practica.services.interfaces;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;

import java.util.Optional;

public interface PersonajeService {

    Iterable<Personaje>obtenerTodos() throws Exception;

    Optional<Personaje> buscarPorNombre(String nombre) throws Exception;

    Personaje crearPersonaje(Personaje personaje) throws Exception;

    Iterable<Personaje>buscarPorRangoEdad(int desde, int hasta)throws Exception;


    Personaje actualizaPersonaje(Personaje personaje, int id)throws Exception;


}
