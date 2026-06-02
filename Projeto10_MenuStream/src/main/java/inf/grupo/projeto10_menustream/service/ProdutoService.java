package inf.grupo.projeto10_menustream.service;

import inf.grupo.projeto10_menustream.dto.ProdutoDTORequest;
import inf.grupo.projeto10_menustream.dto.ProdutoDTOResponse;
import inf.grupo.projeto10_menustream.mapper.ProdutoMapper;
import inf.grupo.projeto10_menustream.model.Produto;
import inf.grupo.projeto10_menustream.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    private final ProdutoMapper produtoMapper = new ProdutoMapper();

    public ProdutoDTOResponse salvar(ProdutoDTORequest produtoDTO){
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return produtoMapper.toProdutoDTO(produto);
    }

    public List<ProdutoDTOResponse> listar(){
        return produtoRepository.findByDisponibilidadeTrue().stream()
                .map(produtoMapper::toProdutoDTO).collect(Collectors.toList());
    }

    public ProdutoDTOResponse atualizar(int id, ProdutoDTORequest produtoDTO){
        Produto produto = produtoRepository.findByIdAndDisponibilidadeTrue(id).
                orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setPreco(produtoDTO.preco());
        produto.setCategoria(produtoDTO.categoria());
        produto.setDisponibilidade(produtoDTO.disponibilidade());
        produto.setImagem(produtoDTO.imagem());

        produto = produtoRepository.save(produto);
        return produtoMapper.toProdutoDTO(produto);
    }

    public void excluir(int id){
        Produto produto = produtoRepository.findByIdAndDisponibilidadeTrue(id).
                orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        produto.setDisponibilidade(false);
        produtoRepository.save(produto);
    }
}
