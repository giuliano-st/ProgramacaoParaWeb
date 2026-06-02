package com.example.projeto10.service;

import com.example.projeto10.dto.ClienteDTORequest;
import com.example.projeto10.dto.ClienteDTOResponse;
import com.example.projeto10.model.Cliente;
import com.example.projeto10.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTOResponse salvar(ClienteDTORequest clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        cliente.setEnderecoEntrega(clienteDTO.enderecoEntrega());
        cliente.setPreferenciaPagamento(clienteDTO.preferenciaPagamento());
        clienteRepository.save(cliente);
        return new ClienteDTOResponse(cliente.getNome(), cliente.getEmail(), cliente.getEnderecoEntrega(), cliente.getPreferenciaPagamento());
    }

    public List<ClienteDTOResponse> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> new ClienteDTOResponse(cliente.getNome(), cliente.getEmail(), cliente.getEnderecoEntrega(), cliente.getPreferenciaPagamento())).toList();
    }

    public ClienteDTOResponse atualizar(Long id, ClienteDTORequest clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        cliente.setEnderecoEntrega( clienteDTO.enderecoEntrega());
        cliente.setPreferenciaPagamento( clienteDTO.preferenciaPagamento());
        clienteRepository.save(cliente);
        return new ClienteDTOResponse(cliente.getNome(), cliente.getEmail(), cliente.getEnderecoEntrega(), cliente.getPreferenciaPagamento());
    }

    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }
}
