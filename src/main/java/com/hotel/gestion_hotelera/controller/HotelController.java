package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Hotel;
import com.hotel.gestion_hotelera.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Endpoints REST de hoteles bajo <code>/hoteles</code>.
 *
 * <ul>
 *   <li><code>GET /hoteles</code>: lista todos</li>
 *   <li><code>GET /hoteles/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /hoteles</code>: crea (201 Created)</li>
 *   <li><code>PUT /hoteles/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /hoteles/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/hoteles")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAll() {
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getById(@PathVariable Integer id) {
        return hotelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Integer id, @RequestBody Hotel details) {
        return ResponseEntity.ok(hotelService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}