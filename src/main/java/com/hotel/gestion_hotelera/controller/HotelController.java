package com.hotel.gestion_hotelera.controller;

import com.hotel.gestion_hotelera.model.Hotel;
import com.hotel.gestion_hotelera.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

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
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Integer id, @RequestBody Hotel details) {
        try {
            return ResponseEntity.ok(hotelService.update(id, details));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}