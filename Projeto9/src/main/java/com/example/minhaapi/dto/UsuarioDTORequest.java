package com.example.minhaapi.dto;

import java.util.List;

public record UsuarioDTORequest(String nome, String email, String senha, String confirmaSenha, List<TelefoneDTORequest> telefones) {
}
/*
Request -> DTO de entrada
Onde colocamos os dados de criação ou atualização
* */
