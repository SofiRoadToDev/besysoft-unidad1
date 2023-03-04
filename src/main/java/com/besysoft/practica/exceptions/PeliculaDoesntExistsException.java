package com.besysoft.practica.exceptions;

public class PeliculaDoesntExistsException extends RuntimeException{
    public PeliculaDoesntExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
