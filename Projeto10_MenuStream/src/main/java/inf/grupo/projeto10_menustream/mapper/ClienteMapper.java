package inf.grupo.projeto10_menustream.mapper;

import inf.grupo.projeto10_menustream.dto.ClienteDTORequest;
import inf.grupo.projeto10_menustream.dto.ClienteDTOResponse;
import inf.grupo.projeto10_menustream.model.Cliente;

public class ClienteMapper {

    public ClienteDTOResponse toClienteDTO(Cliente cliente) {
        if (cliente == null) return null;
        return new ClienteDTOResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getEnderecoEntrega(),
                cliente.getPreferenciaPagamento(),
                cliente.isAtivo()
        );
    }

    public Cliente toEntity(ClienteDTORequest clienteDTORequest) {
        if (clienteDTORequest == null) return null;
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTORequest.nome());
        cliente.setEmail(clienteDTORequest.email());
        cliente.setEnderecoEntrega(clienteDTORequest.enderecoEntrega());
        cliente.setPreferenciaPagamento(clienteDTORequest.preferenciaPagamento());
        cliente.setAtivo(true);
        return cliente;
    }
}
