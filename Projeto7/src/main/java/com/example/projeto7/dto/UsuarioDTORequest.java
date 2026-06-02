package com.example.projeto7.dto;

public record UsuarioDTORequest(long id, String nome, String email, String senha, String confirmaSenha, boolean ativo) {

}
