package com.besysoft.practica.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Personaje {

    private static int id=0;
    private int idPersonaje;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;

    @JsonIgnore
    private List<Pelicula> peliculasAsociadas;


    public Personaje(String nombre, int edad, double peso, String historia) {
        System.out.println("personaje creado id: "+ ++id);
        this.idPersonaje=++id;
        this.peliculasAsociadas= new ArrayList<>();
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
    }

    public Personaje(String nombre){
        this.idPersonaje=++id;
        this.nombre=nombre;
    }
}
