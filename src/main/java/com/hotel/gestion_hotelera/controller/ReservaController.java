package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Reserva;
import com.hotel.gestion_hotelera.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de reservas bajo <code>/reservas</code>.
 *
 * <ul>
 *   <li><code>GET /reservas</code>: lista todos</li>
 *   <li><code>GET /reservas/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /reservas</code>: crea (201 Created)</li>
 *   <li><code>PUT /reservas/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /reservas/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> getAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getById(@PathVariable Integer id) {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva create(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Integer id, @RequestBody Reserva details) {
        return ResponseEntity.ok(reservaService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
