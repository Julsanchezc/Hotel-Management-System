package com.hotel.gestion_hotelera.service;

import com.hotel.gestion_hotelera.model.Usuario;
import com.hotel.gestion_hotelera.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /** Guarda un nuevo usuario */
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /** Retorna todos los usuarios */
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    /** Busca un usuario por su ID */
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Actualiza un usuario existente.
     * Lanza IllegalArgumentException si el ID no existe.
     */
    public Usuario update(Integer id, Usuario datosActualizados) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setApellido(datosActualizados.getApellido());
        existente.setCorreo(datosActualizados.getCorreo());
        existente.setTelefono(datosActualizados.getTelefono());
        existente.setContrasena(datosActualizados.getContrasena());
        existente.setRol(datosActualizados.getRol());

        return usuarioRepository.save(existente);
    }

    /** Elimina un usuario por su ID */
    public void delete(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
