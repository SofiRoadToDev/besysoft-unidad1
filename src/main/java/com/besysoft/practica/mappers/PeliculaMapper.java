package com.besysoft.practica.mappers;

import com.besysoft.practica.dto.PeliculaDTO;
import com.besysoft.practica.entities.Pelicula;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PeliculaMapper {

    PeliculaDTO mapToPeliculaDTO(Pelicula pelicula);

    Pelicula mapToPelicula(PeliculaDTO peliculaDTO);

    default List<PeliculaDTO> mapToListPeliculaDTO(List<Pelicula>peliculas){
        return peliculas.stream().map(p->mapToPeliculaDTO(p)).collect(Collectors.toList());
    }
}
