package com.example.projeto10.dto;

import com.example.projeto10.model.itemPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTOResponse(int id, int clienteId, String clienteNome, List<itemPedidoDTOResponse> itensPedido, Double valorTotal, String status, LocalDateTime dataPedido, LocalDateTime dataEntrega) {
}
