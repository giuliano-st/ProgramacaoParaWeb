package inf.grupo.projeto10_menustream.controller;

import inf.grupo.projeto10_menustream.dto.ProdutoDTORequest;
import inf.grupo.projeto10_menustream.dto.ProdutoDTOResponse;
import inf.grupo.projeto10_menustream.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTOResponse> salvar(@RequestBody ProdutoDTORequest produtoDTO){
        ProdutoDTOResponse produto = produtoService.salvar(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTOResponse>> listar(){
        return ResponseEntity.ok(produtoService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTOResponse> atualizar(@PathVariable int id, @RequestBody ProdutoDTORequest produtoDTO){
        return ResponseEntity.ok(produtoService.atualizar(id,produtoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id){
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
