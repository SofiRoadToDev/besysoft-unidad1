package com.besysoft.practica.repositories.memory.interfaces;

import com.besysoft.practica.dominio.PeliculaMem;

import java.util.Optional;

public interface PeliculaRepository {


    Iterable<PeliculaMem> getAll();
    Iterable<PeliculaMem>getByGenre(String genero);
    Optional<PeliculaMem> getById(int id) ;
    Iterable<PeliculaMem>getByRatingScale(int desde, int hasta);
    Optional<PeliculaMem> getByTitle(String title);

    PeliculaMem createPelicula(PeliculaMem peliculaMem);
    Iterable<PeliculaMem>getByDates(String desde, String hasta);
    PeliculaMem updatePelicula(PeliculaMem peliculaMem, int id);

}
