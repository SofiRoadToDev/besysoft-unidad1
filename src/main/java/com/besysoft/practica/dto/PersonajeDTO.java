package com.besysoft.practica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonajeDTO {

    private Long id;
    private String nombre;
    private int edad;
    private String historia;
}
