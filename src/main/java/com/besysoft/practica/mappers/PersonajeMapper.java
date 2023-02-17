package com.besysoft.practica.mappers;

import com.besysoft.practica.dto.PersonajeDeListaDePelicula;
import com.besysoft.practica.entities.Personaje;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonajeMapper {

    PersonajeMapper INSTANCE= Mappers.getMapper(PersonajeMapper.class);
    PersonajeDeListaDePelicula mapToPersonajeParaListaEnPelicula(Personaje personaje);

}
