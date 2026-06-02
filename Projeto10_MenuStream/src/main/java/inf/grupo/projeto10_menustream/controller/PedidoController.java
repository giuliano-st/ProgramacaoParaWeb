package inf.grupo.projeto10_menustream.controller;

import inf.grupo.projeto10_menustream.dto.PedidoDTORequest;
import inf.grupo.projeto10_menustream.dto.PedidoDTOResponse;
import inf.grupo.projeto10_menustream.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTOResponse> salvar(@RequestBody PedidoDTORequest pedidoDTO){
        PedidoDTOResponse pedido = pedidoService.salvar(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTOResponse>> listar(){
        return ResponseEntity.ok(pedidoService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id){
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
