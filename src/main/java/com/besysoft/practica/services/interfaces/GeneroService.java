package com.besysoft.practica.services.interfaces;

import com.besysoft.practica.dominio.Genero;

import java.util.Optional;

public interface GeneroService {

    Iterable<Genero>obtenerTodos() throws Exception;
    Optional<Genero>buscarPorId(int id) throws Exception;

    Optional<Genero>buscarPorNombre(String nombre) throws Exception;

    Genero crearGenero(Genero genero) throws Exception;

    Genero actualizarGenero(Genero genero, int id)throws Exception;

}
