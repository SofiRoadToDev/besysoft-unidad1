package com.besysoft.practica.dominio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class PersonajeDTO {

    private int id;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;
}
