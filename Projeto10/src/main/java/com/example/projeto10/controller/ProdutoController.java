package com.example.projeto10.controller;

import com.example.projeto10.dto.ProdutoDTORequest;
import com.example.projeto10.dto.ProdutoDTOResponse;
import com.example.projeto10.model.Produto;
import com.example.projeto10.repository.ProdutoRepository;
import com.example.projeto10.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
    }

    @PostMapping()
    @ResponseBody
    public @ResponseStatus ProdutoDTOResponse salvar(@RequestBody ProdutoDTORequest produtoDTO) {
        return produtoService.salvar(produtoDTO);
    }

    @GetMapping()
    @ResponseBody
    public List<ProdutoDTOResponse> listar() {
        return produtoService.listar();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ProdutoDTOResponse editar(@PathVariable Long id, @RequestBody ProdutoDTORequest produtoDTO) {
        return produtoService.atualizar(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletar(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}
