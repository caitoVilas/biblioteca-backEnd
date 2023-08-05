package com.caito.app_biblioteca.exception.custom;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
