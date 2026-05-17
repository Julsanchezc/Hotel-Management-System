package com.hotel.gestion_hotelera.repository;

import com.hotel.gestion_hotelera.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}