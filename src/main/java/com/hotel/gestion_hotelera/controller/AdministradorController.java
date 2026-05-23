package com.hotel.gestion_hotelera.controller;

import com.hotel.gestion_hotelera.model.Administrador;
import com.hotel.gestion_hotelera.service.AdministradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Administrador create(@RequestBody Administrador administrador) {
        return administradorService.save(administrador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> update(@PathVariable Integer id, @RequestBody Administrador details) {
        try {
            return ResponseEntity.ok(administradorService.update(id, details));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        administradorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
