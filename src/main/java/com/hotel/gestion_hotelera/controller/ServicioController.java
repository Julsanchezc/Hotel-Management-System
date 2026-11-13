package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Servicio;
import com.hotel.gestion_hotelera.service.ServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de servicios adicionales bajo <code>/servicios</code>.
 *
 * <ul>
 *   <li><code>GET /servicios</code>: lista todos</li>
 *   <li><code>GET /servicios/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /servicios</code>: crea (201 Created)</li>
 *   <li><code>PUT /servicios/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /servicios/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<Servicio> getAll() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getById(@PathVariable Integer id) {
        return servicioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servicio create(@RequestBody Servicio servicio) {
        return servicioService.save(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> update(@PathVariable Integer id, @RequestBody Servicio details) {
        return ResponseEntity.ok(servicioService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servicioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
