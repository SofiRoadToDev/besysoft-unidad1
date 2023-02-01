package com.besysoft.practica.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class Pelicula {

    private static int id=0;

    private int idPelicula;

    private String titulo;

    private LocalDate fechaCreacion;

    private int calificacion;

    private List<Personaje> personajesAsociados;


    private Genero genero;


    public Pelicula(String titulo, LocalDate fechaCreacion, int calificacion) {
        id++;
        this.idPelicula=id;
        this.personajesAsociados=new ArrayList<>();
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
    }

    public Pelicula(int id,String titulo, LocalDate fechaCreacion, int calificacion) {
        this.idPelicula=id;
        this.personajesAsociados=new ArrayList<>();
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
    }

    public Pelicula() {
        this.personajesAsociados=new ArrayList<>();
        this.idPelicula=id++;
    }

    public static int getIdCounter(){
        return id;
    }


}
