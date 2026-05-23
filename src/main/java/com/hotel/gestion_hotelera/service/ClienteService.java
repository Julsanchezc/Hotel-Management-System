package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Cliente;
import com.hotel.gestion_hotelera.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /** Guarda un nuevo cliente */
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /** Retorna todos los clientes */
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    /** Busca un cliente por su ID */
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    /**
     * Actualiza un cliente existente.
     * Lanza IllegalArgumentException si el ID no existe.
     */
    public Cliente update(Integer id, Cliente datosActualizados) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setApellido(datosActualizados.getApellido());
        existente.setCorreo(datosActualizados.getCorreo());
        existente.setTelefono(datosActualizados.getTelefono());
        existente.setDocumentoIdentidad(datosActualizados.getDocumentoIdentidad());
        existente.setDireccion(datosActualizados.getDireccion());
        existente.setNacionalidad(datosActualizados.getNacionalidad());

        return clienteRepository.save(existente);
    }

    /** Elimina un cliente por su ID */
    public void delete(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
