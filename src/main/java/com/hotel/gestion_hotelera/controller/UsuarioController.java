package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.model.Usuario;
import com.hotel.gestion_hotelera.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Endpoints REST de usuarios bajo <code>/usuarios</code>.
 *
 * <ul>
 *   <li><code>GET /usuarios</code>: lista todos</li>
 *   <li><code>GET /usuarios/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /usuarios</code>: crea (201 Created)</li>
 *   <li><code>PUT /usuarios/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /usuarios/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario details) {
        return ResponseEntity.ok(usuarioService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}