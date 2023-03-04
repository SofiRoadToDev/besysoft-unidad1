package com.besysoft.practica.services.interfaces;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.exceptions.GeneroDoesntExistsException;

import java.util.Optional;

public interface GeneroService {

    Iterable<Genero>obtenerTodos() throws Exception;
    Optional<Genero>buscarPorId(Long id) throws Exception;

    Optional<Genero>buscarPorNombre(String nombre) throws Exception;

    Genero crearGenero(Genero genero) throws Exception;

    Genero actualizarGenero(Genero genero, Long id)throws GeneroDoesntExistsException, Exception;

    void borrarGenero(Long id);

}
