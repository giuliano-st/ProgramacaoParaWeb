package inf.grupo.projeto10_menustream.dto;

public record ClienteDTOResponse(int id, String nome, String email, String enderecoEntrega, String preferenciaPagamento, boolean ativo) {
}
