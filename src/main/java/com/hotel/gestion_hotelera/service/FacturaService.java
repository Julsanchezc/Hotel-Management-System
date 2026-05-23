package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Factura;
import com.hotel.gestion_hotelera.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> findById(Integer id) {
        return facturaRepository.findById(id);
    }

    public Factura update(Integer id, Factura datosActualizados) {
        Factura existente = facturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con id: " + id));

        existente.setFechaFactura(datosActualizados.getFechaFactura());
        existente.setTotal(datosActualizados.getTotal());
        existente.setPago(datosActualizados.getPago());

        return facturaRepository.save(existente);
    }

    public void delete(Integer id) {
        if (!facturaRepository.existsById(id)) {
            throw new IllegalArgumentException("Factura no encontrada con id: " + id);
        }
        facturaRepository.deleteById(id);
    }
}
