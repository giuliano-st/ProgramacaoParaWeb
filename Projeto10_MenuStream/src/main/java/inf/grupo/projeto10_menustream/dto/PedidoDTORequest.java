package inf.grupo.projeto10_menustream.dto;

import java.util.List;

public record PedidoDTORequest(int clienteId, List<ItemPedidoDTORequest> itensPedido, String status) {
}
