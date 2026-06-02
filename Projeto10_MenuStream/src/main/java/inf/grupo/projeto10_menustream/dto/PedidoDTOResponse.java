package inf.grupo.projeto10_menustream.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTOResponse(int id, int clienteId, String clienteNome, List<ItemPedidoDTOResponse> itensPedido, Double valorTotal, String status, LocalDateTime dataPedido, LocalDateTime dataEntrega, boolean ativo) {
}
