package com.example.prova1.repository;

import com.example.prova1.model.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
}
