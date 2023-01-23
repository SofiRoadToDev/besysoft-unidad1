package com.besysoft.practica.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Personaje {

    private String nombre;

    private int edad;

    private double peso;

    private String historia;

    @JsonIgnore
    private List<Pelicula> peliculasAsociadas;


    public Personaje(String nombre, int edad, double peso, String historia) {
        // se inicializa para que después solo haya que cargar datos
        this.peliculasAsociadas= new ArrayList<>();
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
    }

    public Personaje(String nombre){
        this.nombre=nombre;
    }
}
