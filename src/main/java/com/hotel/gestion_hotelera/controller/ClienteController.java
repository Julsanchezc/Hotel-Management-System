package com.hotel.gestion_hotelera.controller;

import org.springframework.http.HttpStatus;
import com.hotel.gestion_hotelera.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.hotel.gestion_hotelera.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Endpoints REST de clientes bajo <code>/clientes</code>.
 *
 * <ul>
 *   <li><code>GET /clientes</code>: lista todos</li>
 *   <li><code>GET /clientes/{id}</code>: busca por ID (404 si no existe)</li>
 *   <li><code>POST /clientes</code>: crea (201 Created)</li>
 *   <li><code>PUT /clientes/{id}</code>: actualiza (404 si no existe)</li>
 *   <li><code>DELETE /clientes/{id}</code>: elimina (204 No Content, 404 si no existe)</li>
 * </ul>
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente details) {
        return ResponseEntity.ok(clienteService.update(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}