package inf.grupo.projeto10_menustream.repository;

import inf.grupo.projeto10_menustream.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByAtivoTrue();

    Optional<Cliente> findByIdAndAtivoTrue(int id);
}
