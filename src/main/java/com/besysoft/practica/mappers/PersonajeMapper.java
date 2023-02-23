package com.besysoft.practica.mappers;

import com.besysoft.practica.dto.PersonajeDTO;
import com.besysoft.practica.entities.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonajeMapper {

    PersonajeMapper INSTANCE= Mappers.getMapper(PersonajeMapper.class);

    List<String>peliculasAsociadas(List<Pelicula> peliculas);

    String mapPeliculaToString(Pelicula pelicula);

    List<PersonajeDTO>mapToListPersonajeDTO(List<Pelicula>personajes);
}
