package com.hotel.gestion_hotelera.controller;

import com.hotel.gestion_hotelera.model.Habitacion;
import com.hotel.gestion_hotelera.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

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
    public Habitacion create(@RequestBody Habitacion habitacion) {
        return habitacionService.save(habitacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> update(@PathVariable Integer id, @RequestBody Habitacion details) {
        try {
            return ResponseEntity.ok(habitacionService.update(id, details));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        habitacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
