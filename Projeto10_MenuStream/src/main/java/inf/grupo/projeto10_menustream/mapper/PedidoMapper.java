package inf.grupo.projeto10_menustream.mapper;

import inf.grupo.projeto10_menustream.dto.ItemPedidoDTOResponse;
import inf.grupo.projeto10_menustream.dto.PedidoDTOResponse;
import inf.grupo.projeto10_menustream.model.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public PedidoDTOResponse toPedidoDTO(Pedido pedido) {
        if (pedido == null) return null;

        List<ItemPedidoDTOResponse> itens = pedido.getItensPedido().stream()
                .map(item -> new ItemPedidoDTOResponse(item.getProduto().getId(), item.getProduto().getNome(), item.getQuantidade(), item.getPrecoUnitario())).collect(Collectors.toList());

        return new PedidoDTOResponse(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNome(),
                itens,
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getDataPedido(),
                pedido.getDataEntrega(),
                pedido.isAtivo()
        );
    }
}
