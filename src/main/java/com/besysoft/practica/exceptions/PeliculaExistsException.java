package com.besysoft.practica.exceptions;

public class PeliculaExistsException extends RuntimeException{

    public PeliculaExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PeliculaExistsException(String message) {
        super(message);
    }
}
