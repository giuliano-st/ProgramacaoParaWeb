package com.example.prova1.service;

import com.example.prova1.dto.PacienteDTORequest;
import com.example.prova1.dto.PacienteDTOResponse;
import com.example.prova1.model.Paciente;
import com.example.prova1.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteDTOResponse savePaciente(PacienteDTORequest pacienteDTO) { //Create
        Paciente paciente = new Paciente();
        paciente.setName(pacienteDTO.name());
        paciente.setEmail(pacienteDTO.email());
        paciente.setCpf(pacienteDTO.cpf());
        pacienteRepository.save(paciente);
        return new PacienteDTOResponse(
                paciente.getId(),
                paciente.getName(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }

    public List<PacienteDTOResponse> listPaciente() {
        List<Paciente> pacientes = (List<Paciente>) pacienteRepository.findAll();
        return pacientes.stream().map(paciente -> new PacienteDTOResponse(paciente.getId(), paciente.getName(), paciente.getEmail(), paciente.getCpf())).toList();
    }

    public PacienteDTOResponse updatePaciente(Long id, PacienteDTORequest pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
        paciente.setName(pacienteDTO.name());
        paciente.setEmail(pacienteDTO.email());
        paciente.setCpf(pacienteDTO.cpf());
        pacienteRepository.save(paciente);
        return new PacienteDTOResponse(paciente.getId(), paciente.getName(), paciente.getEmail(), paciente.getCpf());
    }

    public void deletePaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
        pacienteRepository.delete(paciente);
    }
}
