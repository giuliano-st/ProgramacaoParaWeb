package inf.grupo.projeto10_menustream.service;

import inf.grupo.projeto10_menustream.dto.ItemPedidoDTORequest;
import inf.grupo.projeto10_menustream.dto.ItemPedidoDTOResponse;
import inf.grupo.projeto10_menustream.dto.PedidoDTORequest;
import inf.grupo.projeto10_menustream.dto.PedidoDTOResponse;
import inf.grupo.projeto10_menustream.mapper.PedidoMapper;
import inf.grupo.projeto10_menustream.model.*;
import inf.grupo.projeto10_menustream.repository.ClienteRepository;
import inf.grupo.projeto10_menustream.repository.PedidoRepository;
import inf.grupo.projeto10_menustream.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private final PedidoMapper pedidoMapper = new PedidoMapper();

    public PedidoDTOResponse salvar(PedidoDTORequest pedidoDTO){
        Cliente cliente = clienteRepository.findByIdAndAtivoTrue(pedidoDTO.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(Status.valueOf(pedidoDTO.status().toUpperCase()));
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setDataEntrega(LocalDateTime.now().plusHours(3));
        pedido.setAtivo(true);

        List<ItemPedido> itensPedido = new ArrayList<>();
        double soma = 0;

        for (ItemPedidoDTORequest itemPedidoDTO : pedidoDTO.itensPedido()) {
            Produto produto = produtoRepository.findByIdAndDisponibilidadeTrue(itemPedidoDTO.produtoId()).orElseThrow(() -> new RuntimeException("Produto não disponível!"));

            ItemPedido item = new  ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemPedidoDTO.quantidade());
            item.setPrecoUnitario(produto.getPreco());

            soma += produto.getPreco() * itemPedidoDTO.quantidade();
            itensPedido.add(item);
        }
        pedido.setItensPedido(itensPedido);
        double valornovo = soma *1.10; //Valor furtado pelo garçom
        pedido.setValorTotal(Math.round(valornovo * 100.0) / 100.0);

        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.toPedidoDTO(pedido);
    }

    public List<PedidoDTOResponse> listar(){
        return pedidoRepository.findByAtivoTrue().stream()
                .map(pedidoMapper::toPedidoDTO)
                .collect(Collectors.toList());
    }

    public void excluir(int id){
        Pedido pedido = pedidoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        pedido.setAtivo(false);
        pedidoRepository.save(pedido);
    }
}
