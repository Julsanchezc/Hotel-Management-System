package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Factura;
import com.hotel.gestion_hotelera.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de facturas bajo <code>/facturas</code>.
 *
 * <ul>
 *   <li><code>GET /facturas</code>: lista todos</li>
 *   <li><code>GET /facturas/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /facturas</code>: crea (201 Created)</li>
 *   <li><code>PUT /facturas/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /facturas/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<Factura> getAll() {
        return facturaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getById(@PathVariable Integer id) {
        return facturaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Factura create(@RequestBody Factura factura) {
        return facturaService.save(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> update(@PathVariable Integer id, @RequestBody Factura details) {
        return ResponseEntity.ok(facturaService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
