package com.herysson.menubackend.service;

import com.herysson.menubackend.dto.PedidoDTORequest;
import com.herysson.menubackend.dto.PedidoDTOResponse;
import com.herysson.menubackend.model.Cliente;
import com.herysson.menubackend.model.ItemPedido;
import com.herysson.menubackend.model.Pedido;
import com.herysson.menubackend.model.Produto;
import com.herysson.menubackend.repository.ClienteRepository;
import com.herysson.menubackend.repository.PedidoRepository;
import com.herysson.menubackend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    public PedidoDTOResponse salvar(PedidoDTORequest pedidoDTO){
        Pedido pedido = new Pedido();
        Cliente cliente = clienteRepository.findById(pedidoDTO.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        List<ItemPedido> itensPedido = pedidoDTO.itensPedido().stream().map(itemDTO -> {
            ItemPedido item = new ItemPedido();

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

            item.setPedidoId(pedido);
            item.setProdutoId(produto);
            item.setQuantidade(itemDTO.getQuantidade());

            return item;
        }).toList();
        pedido.setClienteId(cliente);
        pedido.setItensPedido(itensPedido);
        pedido.setValorTotal(pedidoDTO.valorTotal());
        pedido.setStatus(pedidoDTO.status());
        pedido.setDataPedido(pedidoDTO.dataPedido());
        pedido.setDataEntrega(pedidoDTO.dataEntrega());
        pedidoRepository.save(pedido);
        return new PedidoDTOResponse(pedido.getId(), pedido.getClienteId(), pedido.getItensPedido(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataPedido(), pedido.getDataEntrega());
    }

    public PedidoDTOResponse buscarPorId(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        return new PedidoDTOResponse(pedido.getId(), pedido.getClienteId(), pedido.getItensPedido(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataPedido(), pedido.getDataEntrega());
    }

    public List<PedidoDTOResponse> listar(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> new PedidoDTOResponse(pedido.getId(), pedido.getClienteId(), pedido.getItensPedido(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataPedido(), pedido.getDataEntrega())).toList();
    }

    public PedidoDTOResponse atualizar(Long id, PedidoDTORequest pedidoDTO){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        Cliente cliente = clienteRepository.findById(pedidoDTO.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        List<ItemPedido> itensPedido = pedidoDTO.itensPedido().stream().map(itemDTO -> {
            ItemPedido item = new ItemPedido();

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

            item.setPedidoId(pedido);
            item.setProdutoId(produto);
            item.setQuantidade(itemDTO.getQuantidade());

            return item;
        }).toList();
        pedido.setClienteId(cliente);
        pedido.setItensPedido(itensPedido);
        pedido.setValorTotal(pedidoDTO.valorTotal());
        pedido.setStatus(pedidoDTO.status());
        pedido.setDataPedido(pedidoDTO.dataPedido());
        pedido.setDataEntrega(pedidoDTO.dataEntrega());
        pedidoRepository.save(pedido);
        return new PedidoDTOResponse(pedido.getId(), pedido.getClienteId(), pedido.getItensPedido(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataPedido(), pedido.getDataEntrega());
    }

    public void deletar(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        pedidoRepository.delete(pedido);
    }
}
