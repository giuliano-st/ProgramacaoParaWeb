package com.example.prova1.service;

import com.example.prova1.dto.ConsultaDTORequest;
import com.example.prova1.dto.ConsultaDTOResponse;
import com.example.prova1.dto.MedicoDTOResponse;
import com.example.prova1.dto.PacienteDTOResponse;
import com.example.prova1.model.Consulta;
import com.example.prova1.model.Medico;
import com.example.prova1.model.Paciente;
import com.example.prova1.repository.ConsultaRepository;
import com.example.prova1.repository.MedicoRepository;
import com.example.prova1.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public ConsultaDTOResponse saveConsulta(ConsultaDTORequest consultaDTO) {
        if(consultaDTO.price() <= 0) {
            throw new RuntimeException("Valor deve ser maior que 0!");
        }
        if (consultaDTO.appointmentDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar consulta para data/hora no passado!");
        }
        //
        Paciente paciente = pacienteRepository.findById(consultaDTO.patientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        boolean pacienteJaTemConsulta = consultaRepository
                .existsByPatientAndAppointmentDate(paciente, consultaDTO.appointmentDate());

        if (pacienteJaTemConsulta) {
            throw new RuntimeException("Paciente já possui consulta nesse horário");
        }
        //
        Medico medico = medicoRepository.findById(consultaDTO.doctorId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        boolean medicoJaTemConsulta = consultaRepository
                .existsByMedicoAndAppointmentDate(medico, consultaDTO.appointmentDate());

        if (medicoJaTemConsulta) {
            throw new RuntimeException("Medico já possui consulta nesse horário");
        }
        Consulta consulta = new Consulta();
        consulta.setAppointmentDate(consultaDTO.appointmentDate());
        consulta.setPrice(consultaDTO.price());
        consulta.setNotes(consultaDTO.notes());
        consulta.setStatus(consultaDTO.statusConsulta());
        consulta.setPatient(paciente);
        consulta.setMedico(medico);
        consultaRepository.save(consulta);
        return new ConsultaDTOResponse(consulta.getAppointmentDate(), consulta.getPrice(), consulta.getNotes(), consulta.getStatus(),
                new PacienteDTOResponse(
                        consulta.getPatient().getId(),
                        consulta.getPatient().getName(),
                        consulta.getPatient().getEmail(),
                        consulta.getPatient().getCpf()
                ),

                new MedicoDTOResponse(
                        consulta.getMedico().getId(),
                        consulta.getMedico().getName(),
                        consulta.getMedico().getSpeciality(),
                        consulta.getMedico().getCrm()
                ));
    }

    public List<ConsultaDTOResponse> listConsulta() {
        List<Consulta> consultas = (List<Consulta>) consultaRepository.findAll();

        return consultas.stream().map(consulta ->
                new ConsultaDTOResponse(
                        consulta.getAppointmentDate(),
                        consulta.getPrice(),
                        consulta.getNotes(),
                        consulta.getStatus(),

                        new PacienteDTOResponse(
                                consulta.getPatient().getId(),
                                consulta.getPatient().getName(),
                                consulta.getPatient().getEmail(),
                                consulta.getPatient().getCpf()
                        ),

                        new MedicoDTOResponse(
                                consulta.getMedico().getId(),
                                consulta.getMedico().getName(),
                                consulta.getMedico().getSpeciality(),
                                consulta.getMedico().getCrm()
                        )
                )
        ).toList();
    }
}
