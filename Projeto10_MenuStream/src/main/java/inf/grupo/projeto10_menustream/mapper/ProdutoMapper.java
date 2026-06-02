package inf.grupo.projeto10_menustream.mapper;

import inf.grupo.projeto10_menustream.dto.ProdutoDTORequest;
import inf.grupo.projeto10_menustream.dto.ProdutoDTOResponse;
import inf.grupo.projeto10_menustream.model.Produto;

public class ProdutoMapper {

    public ProdutoDTOResponse toProdutoDTO(Produto produto){
        if(produto==null) return null;
        return new ProdutoDTOResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.isDisponibilidade(),
                produto.getImagem()
        );
    }

    public Produto toEntity(ProdutoDTORequest produtoDTO){
        if(produtoDTO==null) return null;
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setPreco(produtoDTO.preco());
        produto.setCategoria(produtoDTO.categoria());
        produto.setDisponibilidade(produtoDTO.disponibilidade());
        produto.setImagem(produtoDTO.imagem());
        return produto;
    }
}
