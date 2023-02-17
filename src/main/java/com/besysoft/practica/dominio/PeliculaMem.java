package com.besysoft.practica.dominio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class PeliculaMem {

    private static int id=0;

    private int idPelicula;

    private String titulo;

    private LocalDate fechaCreacion;

    private int calificacion;

    private List<PersonajeMem> personajesAsociados;


    private GeneroMem generoMem;


    public PeliculaMem(String titulo, LocalDate fechaCreacion, int calificacion) {
        id++;
        this.idPelicula=id;
        this.personajesAsociados=new ArrayList<>();
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
    }


    public PeliculaMem() {
        id++;
        this.personajesAsociados=new ArrayList<>();
        this.idPelicula=id;
    }

    public static int getIdCounter(){
        return id;
    }


}
