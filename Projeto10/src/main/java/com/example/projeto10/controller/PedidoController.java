package com.example.projeto10.controller;

import com.example.projeto10.dto.PedidoDTORequest;
import com.example.projeto10.dto.PedidoDTOResponse;
import com.example.projeto10.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping()
    public @ResponseStatus(org.springframework.http.HttpStatus.CREATED) PedidoDTOResponse salvar(@RequestBody PedidoDTORequest pedidoDTO) {
        return pedidoService.salvar(pedidoDTO);
    }

    @GetMapping()
    @ResponseBody
    public List<PedidoDTOResponse> listar() {
        return pedidoService.listar();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public PedidoDTOResponse editar(@PathVariable Long id, @RequestBody PedidoDTORequest pedidoDTO) {
        return pedidoService.atualizar(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletar(@PathVariable Long id) {
        pedidoService.excluir(id);
    }
}
