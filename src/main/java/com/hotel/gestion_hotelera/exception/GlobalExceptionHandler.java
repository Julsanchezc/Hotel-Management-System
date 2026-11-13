package com.hotel.gestion_hotelera.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejo centralizado de errores para toda la API.
 *
 * <p>Convierte las excepciones del dominio en respuestas HTTP con formato
 * estándar <a href="https://www.rfc-editor.org/rfc/rfc9457">RFC 9457 (Problem Details)</a>.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Recurso inexistente → 404 Not Found. */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Recurso no encontrado");
        return problem;
    }
}
