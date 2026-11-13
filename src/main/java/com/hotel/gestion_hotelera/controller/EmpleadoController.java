package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Empleado;
import com.hotel.gestion_hotelera.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de empleados bajo <code>/empleados</code>.
 *
 * <ul>
 *   <li><code>GET /empleados</code>: lista todos</li>
 *   <li><code>GET /empleados/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /empleados</code>: crea (201 Created)</li>
 *   <li><code>PUT /empleados/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /empleados/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> getAll() {
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable Integer id) {
        return empleadoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado create(@RequestBody Empleado empleado) {
        return empleadoService.save(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody Empleado details) {
        return ResponseEntity.ok(empleadoService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
