package com.besysoft.practica.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Genero {

    private static int id=1;

    private int idGenero;

    private String nombre;

    @JsonIgnore
    List<Pelicula>peliculasDelGenero;

    public Genero(String nombre) {
        id++;
        this.idGenero=id;
        this.peliculasDelGenero=new ArrayList<>();
        this.nombre = nombre;
    }


}
