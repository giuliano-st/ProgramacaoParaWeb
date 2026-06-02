package inf.grupo.projeto10_menustream.controller;

import inf.grupo.projeto10_menustream.dto.ClienteDTORequest;
import inf.grupo.projeto10_menustream.dto.ClienteDTOResponse;
import inf.grupo.projeto10_menustream.service.ClienteService;
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
        ClienteDTOResponse cliente = clienteService.salvar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTOResponse>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTOResponse> atualizar(@PathVariable int id, @RequestBody ClienteDTORequest clienteDTO) {
        return ResponseEntity.ok(clienteService.atualizar(id, clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
