package com.besysoft.practica.exceptions;

public class PeliculaExistsException extends RuntimeException{

    public PeliculaExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
