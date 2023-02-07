package com.besysoft.practica.repositories.database;

import com.besysoft.practica.entities.Pelicula;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PeliculaRepositoryDB extends CrudRepository<Pelicula,Long> {

    Optional<Pelicula>findByTitulo(String titulo)throws Exception ;
    Iterable<Pelicula>findByFechaCreacionBetween (String desde,String hasta);
    Iterable<Pelicula>findByGeneroNombre(String nombre);
    Iterable<Pelicula> findByCalificacionBetween(int desde, int hasta);
}
