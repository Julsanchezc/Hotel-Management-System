package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Rol;
import com.hotel.gestion_hotelera.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /** Guarda un nuevo rol */
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    /** Retorna todos los roles */
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    /** Busca un rol por su ID */
    public Optional<Rol> findById(Integer id) {
        return rolRepository.findById(id);
    }

    /**
     * Actualiza un rol existente.
     * Lanza IllegalArgumentException si el ID no existe.
     */
    public Rol update(Integer id, Rol datosActualizados) {
        Rol existente = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());

        return rolRepository.save(existente);
    }

    /** Elimina un rol por su ID */
    public void delete(Integer id) {
        if (!rolRepository.existsById(id)) {
            throw new IllegalArgumentException("Rol no encontrado con id: " + id);
        }
        rolRepository.deleteById(id);
    }
}
