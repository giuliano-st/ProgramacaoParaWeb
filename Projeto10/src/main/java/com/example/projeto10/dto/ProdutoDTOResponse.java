package com.example.projeto10.dto;

public record ProdutoDTOResponse(int id, String nome, String descricao, Double preco, String categoria, boolean disponibilidade, String imagem) {
}
