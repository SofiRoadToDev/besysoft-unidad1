package com.besysoft.practica.exceptions;

public class FileCantBeOpenedOrRead extends RuntimeException{

    public FileCantBeOpenedOrRead(String message) {
        super(message);
    }

    public FileCantBeOpenedOrRead(String message, Throwable cause) {
        super(message, cause);
    }
}
