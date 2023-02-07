package com.besysoft.practica.repositories.memory.interfaces;

import com.besysoft.practica.dominio.GeneroMem;

import java.util.Optional;

public interface GeneroRepository {

GeneroMem createGenero (GeneroMem generoMem);

Iterable<GeneroMem>getAll();
Optional<GeneroMem> getById(int id);

Optional<GeneroMem> getByNombre(String nombre);
GeneroMem updateGenero(GeneroMem generoMem, int id) ;

}
