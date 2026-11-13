package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Pago;
import com.hotel.gestion_hotelera.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de pagos bajo <code>/pagos</code>.
 *
 * <ul>
 *   <li><code>GET /pagos</code>: lista todos</li>
 *   <li><code>GET /pagos/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /pagos</code>: crea (201 Created)</li>
 *   <li><code>PUT /pagos/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /pagos/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<Pago> getAll() {
        return pagoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getById(@PathVariable Integer id) {
        return pagoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pago create(@RequestBody Pago pago) {
        return pagoService.save(pago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable Integer id, @RequestBody Pago details) {
        return ResponseEntity.ok(pagoService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
