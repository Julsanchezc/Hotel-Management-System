package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Servicio;
import com.hotel.gestion_hotelera.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public Optional<Servicio> findById(Integer id) {
        return servicioRepository.findById(id);
    }

    public Servicio update(Integer id, Servicio datosActualizados) {
        Servicio existente = servicioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setDescripcion(datosActualizados.getDescripcion());
        existente.setPrecio(datosActualizados.getPrecio());

        return servicioRepository.save(existente);
    }

    public void delete(Integer id) {
        if (!servicioRepository.existsById(id)) {
            throw new IllegalArgumentException("Servicio no encontrado con id: " + id);
        }
        servicioRepository.deleteById(id);
    }
}
