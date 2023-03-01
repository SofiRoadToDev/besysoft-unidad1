package com.besysoft.practica.mappers;

import com.besysoft.practica.dto.PersonajeDTO;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;

import java.util.List;
import java.util.stream.Collectors;

public class PersonajeMapper {

    public static PersonajeDTO mapToPersonajeDTO(Personaje personaje){
        PersonajeDTO personajeDTO=new PersonajeDTO();
        if(personaje.getId()!=null){
            personajeDTO.setId(personaje.getId());
        }
        personajeDTO.setEdad(personaje.getEdad());
        personajeDTO.setNombre(personaje.getNombre());
        personajeDTO.setHistoria(personaje.getHistoria());
        personajeDTO.setPeso(personaje.getPeso());
        List<String> peliculas=personaje.getPeliculasAsociadas().stream().map(
                p->p.getTitulo()
        ).collect(Collectors.toList());
        return personajeDTO;
    }

    public static Personaje mapToPersonaje(PersonajeDTO personajeDTO){
        Personaje personaje=new Personaje();
        if(personajeDTO.getId()!=null){
            personaje.setId(personajeDTO.getId());
        }
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHistoria(personajeDTO.getHistoria());
        List<Pelicula>peliculas=personajeDTO.getPeliculasAsociadas().stream().map(Pelicula::new).collect(Collectors.toList());
        personaje.setPeliculasAsociadas(peliculas);
        return personaje;
    }

    public static List<PersonajeDTO>mapToListPersonajeDTO(List<Personaje>personajes){
        return personajes.stream().map(PersonajeMapper::mapToPersonajeDTO).collect(Collectors.toList());
    }

}

