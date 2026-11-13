package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Habitacion;
import com.hotel.gestion_hotelera.service.HabitacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Endpoints REST de habitaciones bajo <code>/habitaciones</code>.
 *
 * <ul>
 *   <li><code>GET /habitaciones</code>: lista todos</li>
 *   <li><code>GET /habitaciones/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /habitaciones</code>: crea (201 Created)</li>
 *   <li><code>PUT /habitaciones/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /habitaciones/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<Habitacion> getAll() {
        return habitacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> getById(@PathVariable Integer id) {
        return habitacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habitacion create(@RequestBody Habitacion habitacion) {
        return habitacionService.save(habitacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> update(@PathVariable Integer id, @RequestBody Habitacion details) {
        return ResponseEntity.ok(habitacionService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        habitacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
