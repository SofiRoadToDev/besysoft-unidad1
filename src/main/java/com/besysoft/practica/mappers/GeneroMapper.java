package com.besysoft.practica.mappers;


import com.besysoft.practica.dto.GeneroDTO;
import com.besysoft.practica.entities.Genero;

import java.util.List;
import java.util.stream.Collectors;

public class GeneroMapper {


    public static GeneroDTO mapToGeneroDTO(Genero genero){
        GeneroDTO generoDTO=new GeneroDTO();
        generoDTO.setNombreGenero(genero.getNombre());
        generoDTO.setCodigo(genero.getId());
        return generoDTO;
    }

    public static Genero mapToGenero(GeneroDTO generoDTO){
        Genero genero=new Genero();
        genero.setId(generoDTO.getCodigo());
        genero.setNombre(generoDTO.getNombreGenero());
        return genero;
    }


    public static List<GeneroDTO> mapToListGeneroDTO(List<Genero>generos){
        return generos.stream().map(GeneroMapper::mapToGeneroDTO).collect(Collectors.toList());
    }


}