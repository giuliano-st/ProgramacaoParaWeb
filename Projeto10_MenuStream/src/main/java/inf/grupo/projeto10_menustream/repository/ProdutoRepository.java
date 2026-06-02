package inf.grupo.projeto10_menustream.repository;

import inf.grupo.projeto10_menustream.dto.ProdutoDTOResponse;
import inf.grupo.projeto10_menustream.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByDisponibilidadeTrue();
    Optional<Produto> findByIdAndDisponibilidadeTrue(int id);
}