package com.besysoft.practica.mappers;

import com.besysoft.practica.dto.PeliculaDTO;
import com.besysoft.practica.entities.Pelicula;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses={PersonajeMapper.class, GeneroMapper.class}
)
public interface PeliculaMapper {

    PeliculaMapper INSTANCE= Mappers.getMapper(PeliculaMapper.class);

    @Mapping(source="fechaCreacion",target="fechaEstreno")
    @Mapping(source = "pelicula.genero.nombre",target = "genero")
    PeliculaDTO mapToPeliculaDTO(Pelicula pelicula);

    @Mapping(source="fechaEstreno",target="fechaCreacion")
    Pelicula mapToPelicula(PeliculaDTO peliculaDTO);



    List<PeliculaDTO>mapToListPeliculaDTO(List<Pelicula>peliculas);




}
