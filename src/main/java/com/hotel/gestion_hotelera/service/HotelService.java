package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Hotel;
import com.hotel.gestion_hotelera.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /** Guarda un nuevo hotel */
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    /** Retorna todos los hoteles */
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    /** Busca un hotel por su ID */
    public Optional<Hotel> findById(Integer id) {
        return hotelRepository.findById(id);
    }

    /**
     * Actualiza un hotel existente.
     * Lanza IllegalArgumentException si el ID no existe.
     */
    public Hotel update(Integer id, Hotel datosActualizados) {
        Hotel existente = hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setDireccion(datosActualizados.getDireccion());
        existente.setCiudad(datosActualizados.getCiudad());
        existente.setTelefono(datosActualizados.getTelefono());
        existente.setCorreo(datosActualizados.getCorreo());
        existente.setCategoria(datosActualizados.getCategoria());
        existente.setEstado(datosActualizados.getEstado());

        return hotelRepository.save(existente);
    }

    /** Elimina un hotel por su ID */
    public void delete(Integer id) {
        if (!hotelRepository.existsById(id)) {
            throw new IllegalArgumentException("Hotel no encontrado con id: " + id);
        }
        hotelRepository.deleteById(id);
    }
}
