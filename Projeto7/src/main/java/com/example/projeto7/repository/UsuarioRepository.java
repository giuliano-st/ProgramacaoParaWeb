package com.example.projeto7.repository;

import com.example.projeto7.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    List<Usuario> findByNomeContaining(String nome);
}
