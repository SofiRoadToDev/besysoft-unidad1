package com.besysoft.practica.repositories.memory.interfaces;

import com.besysoft.practica.dominio.Pelicula;

import java.util.Optional;

public interface PeliculaRepository {


    Iterable<Pelicula> getAll();
    Iterable<Pelicula>getByGenre(String genero);
    Optional<Pelicula> getById(int id) ;
    Iterable<Pelicula>getByRatingScale(int desde,int hasta);
    Optional<Pelicula> getByTitle(String title);

    Pelicula createPelicula(Pelicula pelicula);
    Iterable<Pelicula>getByDates(String desde, String hasta);
    Pelicula updatePelicula(Pelicula pelicula, int id);

}
