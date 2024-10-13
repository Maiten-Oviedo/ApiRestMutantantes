package org.example.parcial1magneto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Captura de excepción para todo dna que no cumplan con los requisitos planteados
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("El ADN no cumple con estructura adecuada: " + e.getMessage());
    }

    //Captura de excepción para dna que no es arreglo
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleNotReadableExceptions(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("El ADN no es un arreglo: " + e.getMessage());
    }

    //Captura general para excepciones no contepladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        return ResponseEntity.badRequest().body("Petición realizada con errores" + e.getMessage());
    }
}
