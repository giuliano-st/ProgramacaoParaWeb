package com.example.projeto10.service;

import com.example.projeto10.dto.ProdutoDTORequest;
import com.example.projeto10.dto.ProdutoDTOResponse;
import com.example.projeto10.model.Produto;
import com.example.projeto10.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTOResponse salvar(ProdutoDTORequest produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setPreco(produtoDTO.preco());
        produto.setCategoria(produtoDTO.categoria());
        produto.setDisponibilidade(produtoDTO.disponibilidade());
        produto.setImagem(produtoDTO.imagem());
        produtoRepository.save(produto);
        return new ProdutoDTOResponse(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCategoria(), produto.isDisponibilidade(), produto.getImagem());
    }

    public List<ProdutoDTOResponse> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ProdutoDTOResponse(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCategoria(), produto.isDisponibilidade(), produto.getImagem())).toList();
    }

    public ProdutoDTOResponse atualizar(Long id, ProdutoDTORequest produtoDTO) {
        Produto prod = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        prod.setNome(produtoDTO.nome());
        prod.setDescricao(produtoDTO.descricao());
        prod.setPreco(produtoDTO.preco());
        prod.setCategoria(produtoDTO.categoria());
        prod.setDisponibilidade(produtoDTO.disponibilidade());
        prod.setImagem(produtoDTO.imagem());
        produtoRepository.save(prod);
        return new ProdutoDTOResponse(prod.getId(), prod.getNome(), prod.getDescricao(), prod.getPreco(), prod.getCategoria(), prod.isDisponibilidade(), prod.getImagem());
    }

    public void excluir(Long id) {
        Produto prod = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        produtoRepository.delete(prod);
    }
}