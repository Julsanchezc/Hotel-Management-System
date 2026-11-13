package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Administrador;
import com.hotel.gestion_hotelera.service.AdministradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de administradores bajo <code>/administradores</code>.
 *
 * <ul>
 *   <li><code>GET /administradores</code>: lista todos</li>
 *   <li><code>GET /administradores/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /administradores</code>: crea (201 Created)</li>
 *   <li><code>PUT /administradores/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /administradores/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public List<Administrador> getAll() {
        return administradorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getById(@PathVariable Integer id) {
        return administradorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Administrador create(@RequestBody Administrador administrador) {
        return administradorService.save(administrador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> update(@PathVariable Integer id, @RequestBody Administrador details) {
        return ResponseEntity.ok(administradorService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        administradorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
