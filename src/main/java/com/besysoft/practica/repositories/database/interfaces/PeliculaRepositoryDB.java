package com.besysoft.practica.repositories.database.interfaces;

import com.besysoft.practica.entities.Pelicula;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PeliculaRepositoryDB extends CrudRepository<Pelicula,Long> {

    Optional<Pelicula>findByTitulo(String titulo) throws Exception;
    Iterable<Pelicula>findByFechaGreatherThanEqualAndFechaLessThanEqual (String desde,String hasta);
    Iterable<Pelicula>findByGeneroNombre(String nombre)throws Exception;
    Iterable<Pelicula>findByCalificacionGreatherThanEqualAndCalificacionLessThanEqual(int desde,int hasta) throws Exception;
}
