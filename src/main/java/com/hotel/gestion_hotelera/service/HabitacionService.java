package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Habitacion;
import com.hotel.gestion_hotelera.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> findById(Integer id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion save(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Habitacion update(Integer id, Habitacion details) {
        return habitacionRepository.findById(id).map(habitacion -> {
            habitacion.setNumero(details.getNumero());
            habitacion.setTipo(details.getTipo());
            habitacion.setCapacidad(details.getCapacidad());
            habitacion.setPrecio(details.getPrecio());
            habitacion.setDisponibilidad(details.getDisponibilidad());
            habitacion.setEstado(details.getEstado());
            habitacion.setPiso(details.getPiso());
            return habitacionRepository.save(habitacion);
        }).orElseThrow(() -> new RuntimeException("Habitación no encontrada con id: " + id));
    }

    public void delete(Integer id) {
        habitacionRepository.deleteById(id);
    }
}
