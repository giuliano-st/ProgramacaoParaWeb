package com.example.prova1.repository;

import com.example.prova1.model.Consulta;
import com.example.prova1.model.Medico;
import com.example.prova1.model.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends CrudRepository<Consulta, Integer> {
    boolean existsByPatientAndAppointmentDate(Paciente patient, LocalDateTime appointmentDate);
    boolean existsByMedicoAndAppointmentDate(Medico doctor, LocalDateTime appointmentDate);
}
