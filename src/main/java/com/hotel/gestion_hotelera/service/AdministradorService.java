package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Administrador;
import com.hotel.gestion_hotelera.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    /** Guarda un nuevo administrador */
    public Administrador save(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /** Retorna todos los administradores */
    public List<Administrador> findAll() {
        return administradorRepository.findAll();
    }

    /** Busca un administrador por su ID */
    public Optional<Administrador> findById(Integer id) {
        return administradorRepository.findById(id);
    }

    /**
     * Actualiza un administrador existente.
     * Lanza IllegalArgumentException si el ID no existe.
     */
    public Administrador update(Integer id, Administrador datosActualizados) {
        Administrador existente = administradorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setApellido(datosActualizados.getApellido());
        existente.setCorreo(datosActualizados.getCorreo());
        existente.setTelefono(datosActualizados.getTelefono());
        existente.setNivelAcceso(datosActualizados.getNivelAcceso());
        existente.setFechaAsignacion(datosActualizados.getFechaAsignacion());
        existente.setEstado(datosActualizados.getEstado());
        existente.setUsuario(datosActualizados.getUsuario());
        existente.setHotel(datosActualizados.getHotel());

        return administradorRepository.save(existente);
    }

    /** Elimina un administrador por su ID */
    public void delete(Integer id) {
        if (!administradorRepository.existsById(id)) {
            throw new IllegalArgumentException("Administrador no encontrado con id: " + id);
        }
        administradorRepository.deleteById(id);
    }
}
