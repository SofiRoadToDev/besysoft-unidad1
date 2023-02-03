package com.besysoft.practica.services.interfaces;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;

import java.util.Optional;

public interface PeliculaService {

    Iterable<Pelicula>obtenerTodos() throws Exception;
    Optional<Pelicula> buscarPorId(int id) throws Exception;

    Optional<Pelicula>buscarPorTitulo(String titulo) throws Exception;

    Pelicula crearPelicula(Pelicula pelicula) throws Exception;

    Iterable<Pelicula>buscarPorRangoCalificacion(int desde, int hasta)throws Exception;

    Iterable<Pelicula>buscarPorGenero(String genero) throws Exception;
    Pelicula actualizarPelicula(Pelicula pelicula, int id)throws Exception;
}
