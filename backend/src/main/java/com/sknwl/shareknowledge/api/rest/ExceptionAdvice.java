package com.sknwl.shareknowledge.api.rest;

import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleInternalError(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFound(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}