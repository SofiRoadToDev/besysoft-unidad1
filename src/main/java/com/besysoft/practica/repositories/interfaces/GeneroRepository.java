package com.besysoft.practica.repositories.interfaces;

import com.besysoft.practica.dominio.Genero;

import java.util.Optional;

public interface GeneroRepository {

Genero createGenero (Genero genero);

Iterable<Genero>getAll();
Optional<Genero> getById(int id);

Optional<Genero> getByNombre(String nombre);
Genero updateGenero(Genero genero, int id) ;

}
