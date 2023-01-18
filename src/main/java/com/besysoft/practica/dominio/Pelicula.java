package com.besysoft.practica.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Pelicula {

    private String titulo;

    private LocalDate fechaCreacion;

    private int calificacion;

    private List<Personaje> personajesAsociados;


    private Genero genero;


    public Pelicula(String titulo, LocalDate fechaCreacion, int calificacion) {
        this.personajesAsociados=new ArrayList<>();
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
    }

    public Pelicula() {
    }


}
