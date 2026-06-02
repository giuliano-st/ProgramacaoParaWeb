package com.example.projeto10.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTORequest(int clienteId, List<PedidoItemDTORequest> itensPedido, String status) {
}
