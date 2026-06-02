package com.example.projeto6.repository;

import com.example.projeto6.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);
    List<Pessoa> findByNomeContaining(String nome);
}
