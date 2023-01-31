package com.besysoft.practica.dominio.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class PeliculaDTO {

    private int id;

    private String titulo;

    private LocalDate fechaCreacion;

    private int calificacion;

    private List<String> personajes;

    private String genero;


    public PeliculaDTO(int id,String titulo, LocalDate fechaCreacion, int calificacion, String genero) {
        this.id=id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.genero = genero;
    }
}
