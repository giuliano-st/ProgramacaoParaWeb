package com.example.projeto10.controller;

import com.example.projeto10.dto.ClienteDTORequest;
import com.example.projeto10.dto.ClienteDTOResponse;
import com.example.projeto10.model.Cliente;
import com.example.projeto10.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTOResponse> salvar(@RequestBody ClienteDTORequest clienteDTO) {
        ClienteDTOResponse response = clienteService.salvar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTOResponse>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTOResponse> editar(@PathVariable Long id, @RequestBody ClienteDTORequest clienteDTO) {
        return ResponseEntity.ok(clienteService.atualizar(id, clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
