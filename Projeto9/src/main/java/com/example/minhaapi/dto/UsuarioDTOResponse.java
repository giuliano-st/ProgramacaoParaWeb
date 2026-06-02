package com.example.minhaapi.dto;

import java.util.List;

public record UsuarioDTOResponse(String nome, String email, List<TelefoneDTOResponse> telefones) {
}
/*
Response -> DTO de saida
Onde buscamos os dados já processados
* */
