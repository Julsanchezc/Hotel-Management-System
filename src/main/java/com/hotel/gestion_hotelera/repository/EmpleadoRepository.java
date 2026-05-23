package com.hotel.gestion_hotelera.repository;

import com.hotel.gestion_hotelera.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByCorreo(String correo);
    List<Empleado> findByHotelId(Integer idHotel);
    List<Empleado> findByDepartamento(String departamento);
}
