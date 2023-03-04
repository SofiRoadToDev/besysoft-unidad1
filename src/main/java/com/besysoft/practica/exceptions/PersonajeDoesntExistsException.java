package com.besysoft.practica.exceptions;

public class PersonajeDoesntExistsException extends RuntimeException{
    public PersonajeDoesntExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
