package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.exception.ResourceNotFoundException;
import com.hotel.gestion_hotelera.model.Pago;
import com.hotel.gestion_hotelera.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Lógica de negocio y operaciones CRUD de pagos.
 *
 * <p>Las operaciones sobre un ID inexistente lanzan {@link ResourceNotFoundException}.</p>
 */
@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> findById(Integer id) {
        return pagoRepository.findById(id);
    }

    public Pago update(Integer id, Pago datosActualizados) {
        Pago existente = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + id));

        existente.setFechaPago(datosActualizados.getFechaPago());
        existente.setMonto(datosActualizados.getMonto());
        existente.setMetodoPago(datosActualizados.getMetodoPago());
        existente.setReserva(datosActualizados.getReserva());

        return pagoRepository.save(existente);
    }

    public void delete(Integer id) {
        if (!pagoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pago no encontrado con id: " + id);
        }
        pagoRepository.deleteById(id);
    }
}
