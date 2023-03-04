package com.besysoft.practica.exceptions;

public class GeneroExistsException extends RuntimeException{
    public GeneroExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneroExistsException(String message) {
        super(message);
    }
}
