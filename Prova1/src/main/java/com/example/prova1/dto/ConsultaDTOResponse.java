package com.example.prova1.dto;

import com.example.prova1.model.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaDTOResponse(LocalDateTime appointmentDate, double price, String notes, StatusConsulta statusConsulta, PacienteDTOResponse patient, MedicoDTOResponse medico) {
}
