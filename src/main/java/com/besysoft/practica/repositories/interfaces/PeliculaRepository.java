package com.besysoft.practica.repositories.interfaces;

import com.besysoft.practica.dominio.Pelicula;

public interface PeliculaRepository {


    Iterable<Pelicula> getAllFromSampleData();
    Iterable<Pelicula>getByGenre(String title);
    Pelicula getById() throws Exception;
    Iterable<Pelicula>getByRatingScale(int desde,int hasta);
    Pelicula getByTitle(String title) throws Exception;

    void createPelicula(Pelicula pelicula) throws Exception;

    void updatePelicula(Pelicula pelicula)throws Exception;

}
