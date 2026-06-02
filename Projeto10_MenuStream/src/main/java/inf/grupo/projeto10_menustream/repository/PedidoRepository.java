package inf.grupo.projeto10_menustream.repository;

import inf.grupo.projeto10_menustream.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByAtivoTrue();
    Optional<Pedido> findByIdAndAtivoTrue(int id);

    Arrays findByAtivoTrue(boolean ativo);
}