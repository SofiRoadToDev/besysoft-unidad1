package com.besysoft.practica.exceptions;

public class GeneroDoesntExistsException extends RuntimeException{
    public GeneroDoesntExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneroDoesntExistsException(String message) {
        super(message);
    }
}
