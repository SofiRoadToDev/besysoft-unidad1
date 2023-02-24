package com.besysoft.practica.mappers;

import com.besysoft.practica.dto.PeliculaDTO;
import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class PeliculaMapper {

    private static SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
    public static Pelicula mapToPelicula(PeliculaDTO peliculaDTO)  {

        Pelicula pelicula=new Pelicula();
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setId(peliculaDTO.getId());
        pelicula.setCalificacion(peliculaDTO.getCalificacion());
        try {
            pelicula.setFechaCreacion(formatter.parse(peliculaDTO.getFechaEstreno()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Genero genero=new Genero(peliculaDTO.getGenero());
        pelicula.setGenero(genero);
        List<Personaje> personajesAsociados=peliculaDTO.getPersonajes().stream().map(
                p->new Personaje(p)
        ).collect(Collectors.toList());
        pelicula.setPersonajesAsociados(personajesAsociados);
        return pelicula;
    }

    public static PeliculaDTO mapToPeliculaDTO(Pelicula pelicula){
        PeliculaDTO peliculaDTO=new PeliculaDTO();
        peliculaDTO.setCalificacion(pelicula.getCalificacion());
        peliculaDTO.setTitulo(pelicula.getTitulo());
        peliculaDTO.setFechaEstreno(formatter.format(pelicula.getFechaCreacion()));
        peliculaDTO.setGenero(pelicula.getGenero().getNombre());
        List<String>personajesAsociados=pelicula.getPersonajesAsociados().stream().map(
                p->p.getNombre()
        ).collect(Collectors.toList());
        peliculaDTO.setPersonajes(personajesAsociados);
        return peliculaDTO;
    }

    public static List<PeliculaDTO>mapToListPeliculaDTO(List<Pelicula>peliculas){
        return peliculas.stream().map(PeliculaMapper::mapToPeliculaDTO).collect(Collectors.toList());
    }

    public static List<Pelicula>mapToPelicula(List<PeliculaDTO>peliculaDTOS) {
        return peliculaDTOS.stream().map(PeliculaMapper::mapToPelicula).collect(Collectors.toList());
    }


}

