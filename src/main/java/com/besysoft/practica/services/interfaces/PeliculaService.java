package com.besysoft.practica.services.interfaces;

import com.besysoft.practica.entities.Pelicula;

import java.util.Optional;

public interface PeliculaService {

    Iterable<Pelicula>obtenerTodos() throws Exception;

    Optional<Pelicula>buscarPorTitulo(String titulo) throws Exception;

    Pelicula crearPelicula(Pelicula pelicula) throws Exception;

    Iterable<Pelicula>buscarPorRangoCalificacion(int desde, int hasta)throws Exception;

    Iterable<Pelicula>buscarPorGenero(String genero) throws Exception;
    Pelicula actualizarPelicula(Pelicula pelicula,Long id)throws Exception;

    Iterable<Pelicula> buscarPorRangoFechas(String desde, String hasta)throws Exception;

    void borrarPelicula(Long id);
}
