package com.besysoft.practica.services.interfaces;

import com.besysoft.practica.entities.Genero;

import java.util.Optional;

public interface GeneroService {

    Iterable<Genero>obtenerTodos() throws Exception;
    Optional<Genero>buscarPorId(Long id) throws Exception;

    Optional<Genero>buscarPorNombre(String nombre) throws Exception;

    Genero crearGenero(Genero genero) throws Exception;

    Genero actualizarGenero(Genero genero, int id)throws Exception;

}
