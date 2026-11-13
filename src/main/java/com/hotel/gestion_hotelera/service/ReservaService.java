package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.exception.ResourceNotFoundException;
import com.hotel.gestion_hotelera.model.Reserva;
import com.hotel.gestion_hotelera.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Lógica de negocio y operaciones CRUD de reservas.
 *
 * <p>Las operaciones sobre un ID inexistente lanzan {@link ResourceNotFoundException}.</p>
 */
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva update(Integer id, Reserva details) {
        return reservaRepository.findById(id).map(reserva -> {
            reserva.setFechaInicio(details.getFechaInicio());
            reserva.setFechaFin(details.getFechaFin());
            reserva.setEstado(details.getEstado());
            reserva.setUsuario(details.getUsuario());
            reserva.setHabitacion(details.getHabitacion());
            // Si en la entrega 1 incluimos servicios adicionales en la reserva:
            if (details.getServicios() != null) {
                reserva.setServicios(details.getServicios());
            }
            return reservaRepository.save(reserva);
        }).orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
    }

    public void delete(Integer id) {
        if (!reservaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reserva no encontrada con id: " + id);
        }
        reservaRepository.deleteById(id);
    }
}
