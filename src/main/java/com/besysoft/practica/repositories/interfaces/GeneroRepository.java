package com.besysoft.practica.repositories.interfaces;

import com.besysoft.practica.dominio.Genero;

import java.util.Optional;

public interface GeneroRepository {

void createGenero (Genero genero) throws  Exception;

Iterable<Genero>getAll();
Optional<Genero> getById(int id) throws Exception;

Optional<Genero> getByNombre(String nombre)throws Exception;
void updateGenero(Genero genero, int id) throws Exception;

}
