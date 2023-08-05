package com.caito.app_biblioteca.exception;

import com.caito.app_biblioteca.exception.custom.BadRequestException;
import com.caito.app_biblioteca.exception.custom.NoContentException;
import com.caito.app_biblioteca.exception.custom.NotFoundException;
import com.caito.app_biblioteca.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ErrorDTO> notFoundException(Exception e, HttpServletRequest request){
        ErrorDTO response = ErrorDTO.builder()
                .code(404)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .url(request.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    protected ResponseEntity<ErrorDTO> noContentException(Exception e, HttpServletRequest request){
        ErrorDTO response = ErrorDTO.builder()
                .code(204)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .url(request.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDTO> badRequestException(Exception e, HttpServletRequest request){
        ErrorDTO response = ErrorDTO.builder()
                .code(400)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .url(request.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
