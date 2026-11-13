package com.hotel.gestion_hotelera.exception;

/**
 * Se lanza cuando se busca, actualiza o elimina un recurso cuyo ID no existe.
 *
 * <p>{@link GlobalExceptionHandler} la traduce a una respuesta HTTP 404,
 * así los controladores no necesitan bloques try/catch.</p>
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
