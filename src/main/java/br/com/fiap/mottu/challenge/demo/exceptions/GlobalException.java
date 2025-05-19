package br.com.fiap.mottu.challenge.demo.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ApiError handleRuntimeException(RuntimeException ex) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Erro inesperado: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiError handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Argumento inválido: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiError handleGenericException(Exception ex) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno: " + ex.getMessage());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiError handleNotFoundException(EntityNotFoundException ex) {
        return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

}