package com.besysoft.practica.exceptions;

public class PersonajeExistsException extends RuntimeException{
    public PersonajeExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonajeExistsException(String message) {
        super(message);
    }
}
