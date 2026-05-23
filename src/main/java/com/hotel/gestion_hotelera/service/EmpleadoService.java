package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Empleado;
import com.hotel.gestion_hotelera.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /** Guarda un nuevo empleado */
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /** Retorna todos los empleados */
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    /** Busca un empleado por su ID */
    public Optional<Empleado> findById(Integer id) {
        return empleadoRepository.findById(id);
    }

    /**
     * Actualiza un empleado existente.
     * Lanza IllegalArgumentException si el ID no existe.
     */
    public Empleado update(Integer id, Empleado datosActualizados) {
        Empleado existente = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setApellido(datosActualizados.getApellido());
        existente.setCorreo(datosActualizados.getCorreo());
        existente.setTelefono(datosActualizados.getTelefono());
        existente.setCargo(datosActualizados.getCargo());
        existente.setDepartamento(datosActualizados.getDepartamento());
        existente.setFechaContratacion(datosActualizados.getFechaContratacion());
        existente.setSalario(datosActualizados.getSalario());
        existente.setEstado(datosActualizados.getEstado());
        existente.setHotel(datosActualizados.getHotel());

        return empleadoRepository.save(existente);
    }

    /** Elimina un empleado por su ID */
    public void delete(Integer id) {
        if (!empleadoRepository.existsById(id)) {
            throw new IllegalArgumentException("Empleado no encontrado con id: " + id);
        }
        empleadoRepository.deleteById(id);
    }
}
