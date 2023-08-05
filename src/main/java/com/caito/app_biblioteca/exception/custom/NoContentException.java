package com.caito.app_biblioteca.exception.custom;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

public class NoContentException extends RuntimeException{
    public NoContentException(String message) {
        super(message);
    }
}
