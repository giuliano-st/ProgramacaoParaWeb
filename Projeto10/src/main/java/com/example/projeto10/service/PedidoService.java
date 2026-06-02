package com.example.projeto10.service;

import com.example.projeto10.dto.PedidoDTORequest;
import com.example.projeto10.dto.PedidoDTOResponse;
import com.example.projeto10.model.Cliente;
import com.example.projeto10.model.Pedido;
import com.example.projeto10.model.itemPedido;
import com.example.projeto10.model.Produto;
import com.example.projeto10.repository.ClienteRepository;
import com.example.projeto10.repository.PedidoRepository;
import com.example.projeto10.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoDTOResponse salvar(PedidoDTORequest pedidoDTO) {
        Pedido pedido = new Pedido();
        Cliente cliente = clienteRepository.findById(pedidoDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setClienteId(cliente);

        List<itemPedido> itensConstruidos = pedidoDTO.itensPedido().stream().map(itemDTO -> {
            itemPedido item = new itemPedido();
            item.setPedido(pedido);
            item.setQuantidade(itemDTO.quantidade());

            Produto prod = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            item.setProduto(prod);
            return item;
        }).toList();

        pedido.setItensPedido(itensConstruidos);
        double subtotal = itensConstruidos.stream()
                .mapToDouble(item ->
                        item.getProduto().getPreco() * item.getQuantidade()
                )
                .sum();

        double total = subtotal * 1.10;

        pedido.setValorTotal(total);
        pedido.setStatus(pedidoDTO.status());
        pedido.setDataPedido(pedidoDTO.dataPedido());
        pedido.setDataEntrega(pedidoDTO.dataEntrega());

        pedidoRepository.save(pedido);
        return new PedidoDTOResponse(pedido.getClienteId().getId(), pedido.getItensPedido(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataPedido(), pedido.getDataEntrega());
    }

    public List<PedidoDTOResponse> listar() {
        List<Pedido> pedidos = (List<Pedido>) pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> new PedidoDTOResponse(pedido.getClienteId().getId(), pedido.getItensPedido(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataPedido(), pedido.getDataEntrega())).toList();
    }

    public PedidoDTOResponse atualizar(Long id, PedidoDTORequest pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(pedidoDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        pedido.setClienteId(cliente);
        pedido.setStatus(pedidoDTO.status());
        pedido.setDataPedido(pedidoDTO.dataPedido());
        pedido.setDataEntrega(pedidoDTO.dataEntrega());
        pedido.getItensPedido().clear();

        List<itemPedido> novosItens = pedidoDTO.itensPedido()
                .stream()
                .map(itemDTO -> {

                    Produto produto = produtoRepository.findById(itemDTO.produtoId())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

                    itemPedido item = new itemPedido();

                    item.setPedido(pedido);
                    item.setProduto(produto);
                    item.setQuantidade(itemDTO.quantidade());

                    return item;

                }).toList();

        pedido.getItensPedido().addAll(novosItens);

        double subtotal = novosItens.stream()
                .mapToDouble(item ->
                        item.getProduto().getPreco() * item.getQuantidade()
                )
                .sum();

        double total = subtotal * 1.10;

        pedido.setValorTotal(total);

        pedidoRepository.save(pedido);

        return new PedidoDTOResponse(
                pedido.getClienteId().getId(),
                pedido.getItensPedido(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getDataPedido(),
                pedido.getDataEntrega()
        );
    }

    public void excluir(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
    }
}
