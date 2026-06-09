package com.herysson.menubackend.dto;

import com.herysson.menubackend.model.Pedido;
import com.herysson.menubackend.model.Produto;

public record ItemPedidoDTORequest(Pedido pedido, Produto produto, int quantidade) {
}
