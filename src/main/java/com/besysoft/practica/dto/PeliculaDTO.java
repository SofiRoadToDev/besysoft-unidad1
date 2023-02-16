package com.besysoft.practica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PeliculaDTO {

    private Long id;

    private String titulo;

    private int calificacion;

    private String genero;

    private String fechaEstreno;//Nombre diferente en la entidad

    private List<String> personajes=new ArrayList<>();
}
