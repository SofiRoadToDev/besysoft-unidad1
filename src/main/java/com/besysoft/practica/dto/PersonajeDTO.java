package com.besysoft.practica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonajeDTO {

    private Long id;
    private String nombre;
    private int edad;
    private String historia;

    private double peso;

    private List<String> peliculasAsociadas=new ArrayList<>();
}
