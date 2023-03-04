package com.besysoft.practica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDTO {

    private int estado;
    private String mensaje;
}
