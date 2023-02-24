package com.besysoft.practica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDTO {

    private Long id;

    private String titulo;

    private int calificacion;

    private String genero;

    private String fechaEstreno;//Nombre diferente en la entidad

    private List<String> personajes=new ArrayList<>();
}
