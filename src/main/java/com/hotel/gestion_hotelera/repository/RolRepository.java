package com.hotel.gestion_hotelera.repository;

import com.hotel.gestion_hotelera.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
