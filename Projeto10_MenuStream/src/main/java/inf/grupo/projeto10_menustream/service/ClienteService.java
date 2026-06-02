package inf.grupo.projeto10_menustream.service;

import inf.grupo.projeto10_menustream.dto.ClienteDTORequest;
import inf.grupo.projeto10_menustream.dto.ClienteDTOResponse;
import inf.grupo.projeto10_menustream.mapper.ClienteMapper;
import inf.grupo.projeto10_menustream.model.Cliente;
import inf.grupo.projeto10_menustream.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private final ClienteMapper clienteMapper = new ClienteMapper();

    public ClienteDTOResponse salvar(ClienteDTORequest clienteDTO){
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toClienteDTO(cliente);
    }

    public List<ClienteDTOResponse> listar(){
        return clienteRepository.findByAtivoTrue().stream().map(clienteMapper::toClienteDTO).collect(Collectors.toList());
    }

    public ClienteDTOResponse atualizar(int id, ClienteDTORequest clienteDTO){
        Cliente cliente = clienteRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new RuntimeException(("Cliente não encontrado!")));
        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        cliente.setEnderecoEntrega(clienteDTO.enderecoEntrega());
        cliente.setPreferenciaPagamento(clienteDTO.preferenciaPagamento());

        cliente = clienteRepository.save(cliente);
        return clienteMapper.toClienteDTO(cliente);
    }

    public void excluir(int id){
        Cliente cliente = clienteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}
