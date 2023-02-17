package com.besysoft.practica.mappers;


import com.besysoft.practica.dto.GeneroDTO;
import com.besysoft.practica.entities.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GeneroMapper {

    GeneroMapper INSTANCE= Mappers.getMapper(GeneroMapper.class);

    @Mapping(source = "codigo",target = "id")
    @Mapping(source = "nombreGenero",target = "nombre")
    Genero mapToGenero(GeneroDTO generoDTO);

    @Mapping(source = "id",target = "codigo")
    @Mapping(source = "nombre",target = "nombreGenero")
    GeneroDTO mapToGeneroDTO(Genero genero);

    default List<GeneroDTO>maptoListGeneroDTO(List<Genero> generos){
        return generos.stream().map(
                g->INSTANCE.mapToGeneroDTO(g))
                .collect(Collectors.toList());
    }
}
