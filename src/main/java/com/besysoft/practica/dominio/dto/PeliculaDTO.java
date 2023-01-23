package com.besysoft.practica.dominio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PeliculaDTO {

    private int id;

    private String titulo;

    private LocalDate fechaCreacion;

    private int calificacion;

    private List<String> personajes;

    private String genero;


    public PeliculaDTO(String titulo, LocalDate fechaCreacion, int calificacion, String genero) {
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.genero = genero;
    }
}
