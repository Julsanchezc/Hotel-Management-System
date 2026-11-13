package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Rol;
import com.hotel.gestion_hotelera.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Endpoints REST de roles bajo <code>/roles</code>.
 *
 * <ul>
 *   <li><code>GET /roles</code>: lista todos</li>
 *   <li><code>GET /roles/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /roles</code>: crea (201 Created)</li>
 *   <li><code>PUT /roles/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /roles/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Rol> getAll() {
        return rolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable Integer id) {
        return rolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rol create(@RequestBody Rol rol) {
        return rolService.save(rol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable Integer id, @RequestBody Rol details) {
        return ResponseEntity.ok(rolService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}